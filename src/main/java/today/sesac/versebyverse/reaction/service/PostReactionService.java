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
        //1.작성자 정보 member 객체로 가져오기 (현재 사용자는 id=1로 하드코딩)
        Member author = memberService.getMember(memberId);

        //2. postId로 post 객체 정보 가져오기
        Post post =
                postRepository.findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(postId)
                        .orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_FOUND,
                                postId.toString()));

        //3. dto 내의 emotion 객체의 감정 꺼내기
        Emotion emotion = postReactionRequestDto.getType();

        //3.dto로 온 감정에 대해 postId에 대한 author의 반응 추가하기
        Reaction reaction = Reaction.createPostReaction(author, post, emotion);

        //4. reaction 객체 저장
        reactionRepository.save(reaction);

        return ReactionUtils.addCountbyReactionType(emotion, postId, POST, reactionRepository);
    }

    @Transactional
    public PostReactionResponseDto deletePostReaction(String type, Long postId, Long memberId) {

        //type을 대문자로 변환
        Emotion emotion = Emotion.valueOf(type.toUpperCase());

        //해당 postId와 memberId와 삭제될 reaction의 id 조회
        Optional<Reaction> reaction =
                reactionRepository.findByMemberIdAndPostIdAndType(memberId, postId, emotion);

        //해당 감정 삭제, 이미 없으면 무시
        if (reaction.isPresent()) {
            reactionRepository.deleteById(reaction.get().getId());
        }
        return ReactionUtils.addCountbyReactionType(emotion, postId, POST, reactionRepository);
    }
}
