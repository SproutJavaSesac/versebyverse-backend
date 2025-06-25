package today.sesac.shoutify.post.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.global.domain.Emotion;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.repository.MemberRepository;
import today.sesac.shoutify.post.dto.request.PostCreateRequestDto;
import today.sesac.shoutify.post.dto.response.PostCreateResponseDto;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Getter
public class PostCommandService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시물 작성 api
     */
    public PostCreateResponseDto savePost(PostCreateRequestDto postCreateRequestDto,
                                          Long memberId) {

        /**
         * 1.작성자 정보 가져오기 (현재 사용자는 id=3으로 하드코딩)
         */
        Member author = getCurrentMember(memberId);
        /**
         * 2.사용자가 작성한 원본내용 설정
         */
        String beforeContents = postCreateRequestDto.getContent();
        /**
         * 3.ai 처리된 afterContents 생성
         */
        String afterContents = processAI(beforeContents);
        /**
         * 감정선택하지 않았을 경우 ai 처리후 string값을 객체 값으로 전환
         */
        //TODO ai 코드로 수정 예정
//        if (request.getEmotionType() == null) {
//            ai 감정 선택 코드 호출
//        }
        Emotion emotionType = Emotion.valueOf(postCreateRequestDto.getEmotionType());
        Concept conceptType = Concept.valueOf(postCreateRequestDto.getConceptType());

        /**
         * 정적 팩토리 메서드
         */
        Post post = Post.createPost(
                author,
                beforeContents,
                afterContents,
                postCreateRequestDto.getTitle(),
                postCreateRequestDto.getImageUrl(),
                emotionType,
                conceptType
        );

        Post savedPost = postRepository.save(post);

        return PostCreateResponseDto.createSuccess(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getAfterContents()
        );
    }

    /**
     * 게시물 삭제
     */
    public void deletePost(Long postId, Long memberId) {
        Post post = validateAuthor(postId, memberId);
        /**
         * soft deleted를 위한 함수
         */
        post.delete();
        postRepository.save(post);
    }

    /**
     * 게시물 숨기기
     */
    public void hidePost(Long postId, Long memberId) {
        Post post = validateAuthor(postId, memberId);
        post.hide();
        postRepository.save(post);
    }

    /**
     * 게시물 숨김 해제
     */
    public void unhidePost(Long postId, Long memberId) {
        Post post = validateAuthor(postId, memberId);
        post.unhide();
        postRepository.save(post);
    }

    /**
     * member 임시 함수
     * 현재 사용자의 id가 1이라고 하드코딩한 메서드
     * 추후 변경 예정
     */
    private Member getCurrentMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
    }

    /**
     * 내용 변환 ai 호출 임시 함수
     */
    private String processAI(String contents) {
        return contents;
    }

    /**
     * 작성자 일치 여부 (권환 확인) 함수
     */
    private Post validateAuthor(Long postId, Long memberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다."));

        if (!post.getAuthor().getId().equals(memberId)) {
            throw new RuntimeException("권한이 없습니다");
        }
        return post;
    }
}
