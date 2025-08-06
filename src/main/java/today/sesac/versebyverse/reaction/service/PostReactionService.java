package today.sesac.versebyverse.reaction.service;

import static today.sesac.versebyverse.reaction.utils.TargetType.POST;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.exception.PostErrorCode;
import today.sesac.versebyverse.post.exception.PostException;
import today.sesac.versebyverse.post.repository.PostRepository;
import today.sesac.versebyverse.reaction.dto.request.PostReactionRequestDto;
import today.sesac.versebyverse.reaction.dto.response.PostReactionResponseDto;
import today.sesac.versebyverse.reaction.entity.Reaction;
import today.sesac.versebyverse.reaction.repository.ReactionRepository;
import today.sesac.versebyverse.reaction.utils.ReactionUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class PostReactionService {

    private final ReactionRepository reactionRepository;
    private final MemberService memberService;
    private final PostRepository postRepository;

    /**
     * 게시글 반응 추가하기.
     */
    public PostReactionResponseDto addPostReaction(PostReactionRequestDto postReactionRequestDto,
                                                   Long postId, Long memberId) {

        savePostReaction(memberId, postId, postReactionRequestDto);

        return ReactionUtils.addCountbyReactionType(postReactionRequestDto.getType(), postId, POST,
                reactionRepository);
    }

    /**
     * 게시글 반응 삭제하기
     *
     * @param type     감정 타입
     * @param postId   게시물 id
     * @param memberId 회원 id
     */
    @Transactional
    public PostReactionResponseDto deletePostReaction(String type, Long postId, Long memberId) {

        //type을 대문자로 변환
        Emotion emotion = Emotion.valueOf(type.toUpperCase());

        //해당 postId에 회원이 눌렀던 감정을 삭제
        deletePostReactionIfExists(postId, memberId, emotion);

        return ReactionUtils.addCountbyReactionType(emotion, postId, POST, reactionRepository);
    }

    /**
     * 게시물 반응 수정하기
     *
     * @param postReactionRequestDto 수정된 감정 타입
     * @param postId                 게시물 id
     * @param memberId               회원 id
     */
    @Transactional
    public PostReactionResponseDto updatePostReaction(PostReactionRequestDto postReactionRequestDto,
                                                      Long postId, Long memberId) {

        //1.회원이 기존에 눌렀던 감정 삭제
        Optional<Reaction>
                existingReaction = reactionRepository.findByMember_IdAndPost_Id(memberId, postId);

        if (existingReaction.isPresent()) {
            reactionRepository.deleteById(existingReaction.get().getId());
            reactionRepository.flush();
        }

        //2.해당 postId에 memberId가 추가한 감정 삽입
        savePostReaction(memberId, postId, postReactionRequestDto);

        return ReactionUtils.addCountbyReactionType(postReactionRequestDto.getType(), postId, POST,
                reactionRepository);
    }

    /**
     * 게시글 감정 삭제.
     *
     * @param postId   게시물 id
     * @param memberId 회원 id
     * @param emotion  감정 타입
     */
    private void deletePostReactionIfExists(Long postId, Long memberId, Emotion emotion) {
        //해당 postId와 memberId와 삭제될 reaction의 id 조회
        Optional<Reaction> reaction =
                reactionRepository.findByMemberIdAndPostIdAndType(memberId, postId, emotion);

        //해당 감정 삭제, 이미 없으면 무시
        if (reaction.isPresent()) {
            reactionRepository.deleteById(reaction.get().getId());
        }
    }

    /**
     * 게시글 감정 추가
     *
     * @param memberId               회원 id
     * @param postId                 게시글 id
     * @param postReactionRequestDto 추가될 감정 dto
     */
    private void savePostReaction(Long memberId, Long postId,
                                  PostReactionRequestDto postReactionRequestDto) {
        //1.작성자 정보 member 객체로 가져오기 (현재 사용자는 id=1로 하드코딩)
        Member author = memberService.getMember(memberId);

        //2. postId로 post 객체 정보 가져오기
        Post post =
                postRepository.findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(postId)
                        .orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_FOUND,
                                postId.toString()));

        //3. dto 내의 emotion 객체의 감정 꺼내기
        Emotion emotion = postReactionRequestDto.getType();

        //4.dto로 온 감정에 대해 postId에 대한 author의 반응 추가하기
        Reaction reaction = Reaction.createPostReaction(author, post, emotion);

        //5. reaction 객체 저장
        reactionRepository.save(reaction);
    }
}
