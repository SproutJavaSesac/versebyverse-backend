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
public class PostCommandService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시물 작성 api
     */
    public PostCreateResponse savePost(PostCreateRequest request, Long memberId) {

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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다"));

        //작성자 권환 확인 필요( 현재 사용자 == 게시글 작성자 비교)

        /**
         * soft deleted를 위한 함수
         */
        post.delete();
        postRepository.save(post);
    }

    /**
     * 게시물 숨기기
     */
    public void hidePost(Long postId) {
        Post post = validateExistedPost(postId);

        //작성자 일치여부 추후 코드 변경 필요
        Member author = getCurrentMember();
        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new RuntimeException("숨김 권한이 없습니다");
        }

        post.hide();
        postRepository.save(post);
    }

    /**
     * 게시물 숨김 해제
     */
    public void unhidePost(Long postId) {
        Post post = validateExistedPost(postId);
        post.unhide();
        postRepository.save(post);
    }


    /**
     * member 임시 함수
     * 임시 데이터의 nickname을 찾아서 진행하도록 함
     * getMember() 생성시 추후 변경 예정
     */
    private Member getCurrentMember() {
        return memberRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
    }

    /**
     * 내용 변환 ai 호출 임시 함수
     */
    private String processAI(String contents) {
        return contents;
    }

    /**
     * 게시물 존재 유무 함수
     */
    private Post validateExistedPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다"));

    }
}
