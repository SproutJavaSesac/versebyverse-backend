package today.sesac.shoutify.post.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.global.domain.Emotion;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.repository.MemberRepository;
import today.sesac.shoutify.post.dto.request.PostCreateRequest;
import today.sesac.shoutify.post.dto.response.PostCreateResponse;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Getter
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시물 작성 api
     */
    public PostCreateResponse savePost(PostCreateRequest request) {

        /**
         * 1.작성자 정보 가져오기
         */
        Member author = getCurrentMember();
        /**
         * 2.사용자가 작성한 원본내용 설정
         */
        String beforeContents = request.getContent();
        /**
         * 3.ai 처리된 afterContents 생성
         */
        String afterContents = processAI(beforeContents);
        /**
         * 감정선택하지 않았을 경우 ai 처리후 string값을 객체 값으로 전환
         */
//        if (request.getEmotionType() == null) {
//            ai 감정 선택 코드 호출
//        }
        Emotion emotionType = Emotion.valueOf(request.getEmotionType());
        Concept conceptType = Concept.valueOf(request.getConceptType());

        /**
         * 정적 팩토리 메서드
         */
        Post post = Post.createPost(
                author,
                beforeContents,
                afterContents,
                request.getTitle(),
                request.getImageUrl(),
                emotionType,
                conceptType
        );

        Post savedPost = postRepository.save(post);

        return PostCreateResponse.createSuccess(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getAfterContents()
        );
    }

    /**
     * 게시물 삭제
     */
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    /**
     * member 임시 함수
     */
    private Member getCurrentMember() {
        return memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    /**
     * 내용 변환 ai 호출 임시 함수
     */
    private String processAI(String contents) {
        return contents;
    }
}
