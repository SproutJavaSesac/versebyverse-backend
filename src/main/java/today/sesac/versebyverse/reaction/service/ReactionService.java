package today.sesac.versebyverse.reaction.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.comment.repository.CommentRepository;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.repository.PostRepository;
import today.sesac.versebyverse.reaction.dto.request.ReactionRequestDto;
import today.sesac.versebyverse.reaction.dto.response.ReactionResponseDto;
import today.sesac.versebyverse.reaction.entity.Reaction;
import today.sesac.versebyverse.reaction.repository.ReactionRepository;
import today.sesac.versebyverse.reaction.utils.ReactionUtils;
import today.sesac.versebyverse.reaction.utils.TargetType;

@Service
@RequiredArgsConstructor
@Transactional
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /**
     * 게시글과 댓글 반응 추가하기.
     */
    public ReactionResponseDto addReaction(ReactionRequestDto reactionRequestDto,
                                           Long targetId, Long memberId,
                                           TargetType targetType) {

        saveReaction(targetType, targetId, memberId, reactionRequestDto);

        return ReactionUtils.addCountbyReactionType(reactionRequestDto.getType(), targetId,
                targetType, reactionRepository);
    }

    /**
     * 게시글과 댓글 반응 삭제하기
     *
     * @param type       감정 타입
     * @param targetId   댓글 or 게시글 id
     * @param memberId   회원 id
     * @param targetType 댓글 or 게시글
     */
    @Transactional
    public ReactionResponseDto deleteReaction(String type, Long targetId, Long memberId,
                                              TargetType targetType) {

        //type을 대문자로 변환
        Emotion emotion = Emotion.valueOf(type.toUpperCase());

        //해당 postId에 회원이 눌렀던 감정을 삭제
        deleteReactionIfExists(targetType, targetId, memberId, emotion);

        return ReactionUtils.addCountbyReactionType(emotion, targetId, targetType,
                reactionRepository);
    }

    /**
     * 반응 수정하기
     *
     * @param reactionRequestDto 수정된 감정 타입
     * @param targetType         수정된 게시글 or 댓글
     * @param targetId           바뀐 게시글 or 댓글의 id
     * @param memberId           회원 id
     */
    @Transactional
    public ReactionResponseDto updateReaction(TargetType targetType, Long targetId,
                                              ReactionRequestDto reactionRequestDto,
                                              Long memberId) {

        //1.회원이 기존에 눌렀던 감정 삭제
        Optional<Reaction>
                existingReaction = findExistingReaction(targetType, targetId, memberId);

        if (existingReaction.isPresent()) {
            reactionRepository.deleteById(existingReaction.get().getId());
            reactionRepository.flush();
        }

        //2.해당 targetId에 memberId가 추가한 감정 삽입
        saveReaction(targetType, targetId, memberId, reactionRequestDto);


        return ReactionUtils.addCountbyReactionType(reactionRequestDto.getType(), targetId,
                targetType,
                reactionRepository);
    }

    /**
     * 감정 삭제.
     *
     * @param targetId 게시물이나 댓글 id
     * @param memberId 회원 id
     * @param emotion  감정 타입
     */
    private void deleteReactionIfExists(TargetType targetType, Long targetId, Long memberId,
                                        Emotion emotion) {
        //해당 targetType, targetId, 회원 id , 감정에 맞는 reaction optional 객체
        Optional<Reaction> reaction = findReactionByType(targetType, targetId, memberId, emotion);

        //해당 감정 삭제, 이미 없으면 무시
        if (reaction.isPresent()) {
            reactionRepository.deleteById(reaction.get().getId());
        }
    }

    /**
     * 감정 추가
     *
     * @param memberId           회원 id
     * @param targetType         게시글 or 댓글
     * @param targetId           수정될 게시글 or 댓글의 id
     * @param reactionRequestDto 추가될 감정 dto
     */
    private void saveReaction(TargetType targetType, Long targetId, Long memberId,
                              ReactionRequestDto reactionRequestDto) {
        //1.작성자 정보 member 객체로 가져오기 (현재 사용자는 id=1로 하드코딩)
        Member author = memberService.getMember(memberId);

        //2. dto 내의 emotion 객체의 감정 꺼내기
        Emotion emotion = reactionRequestDto.getType();

        //3. targetId로 post나 comment 객체 정보 가져오고 reaction 객체 생성
        Reaction reaction;
        if (targetType == TargetType.POST) {
            Post post = postRepository.findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(
                            targetId)
                    .orElseThrow(() -> new RuntimeException("post not found"));
            //dto로 온 감정에 대해 postId에 대한 author의 반응 reaction 객체 생성
            reaction = Reaction.createPostReaction(author, post, emotion);
        } else {
            Comment comment =
                    commentRepository.findByIdAndIsDeletedFalseAndIsReportedFalse(targetId)
                            .orElseThrow(() -> new RuntimeException("comment not found"));
            reaction = Reaction.createCommentReaction(author, comment, emotion);
        }
        //3. reaction 객체 저장
        reactionRepository.save(reaction);
    }

    /**
     * 사용자가 해당 대상에 특정 감정으로 표시했는지 확인
     *
     * @param targetType
     * @param targetId
     * @param memberId
     * @param emotion
     */
    private Optional<Reaction> findReactionByType(TargetType targetType, Long targetId,
                                                  Long memberId, Emotion emotion) {
        if (targetType == TargetType.POST) {
            return reactionRepository.findByMemberIdAndPostIdAndType(memberId, targetId, emotion);
        } else if (targetType == TargetType.COMMENT) {
            return reactionRepository.findByMemberIdAndCommentIdAndType(memberId, targetId,
                    emotion);
        }
        return Optional.empty();
    }

    /**
     * 사용자가 해당 대상에 대해 감정타입과 무관하게 반응했는지 확인
     *
     * @param targetType
     * @param targetId
     * @param memberId
     * @return
     */
    private Optional<Reaction> findExistingReaction(TargetType targetType, Long targetId,
                                                    Long memberId) {
        if (targetType == TargetType.POST) {
            return reactionRepository.findByMember_IdAndPost_Id(memberId, targetId);
        } else if (targetType == TargetType.COMMENT) {
            return reactionRepository.findByMember_IdAndComment_Id(memberId, targetId);
        }
        return Optional.empty();
    }
}
