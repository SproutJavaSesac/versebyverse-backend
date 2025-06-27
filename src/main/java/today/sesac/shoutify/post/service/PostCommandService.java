package today.sesac.shoutify.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.global.exception.PermissionRequiredException;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.service.MemberService;
import today.sesac.shoutify.post.dto.request.PostCreateRequestDto;
import today.sesac.shoutify.post.dto.response.PostCreateResponseDto;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.exception.PostErrorCode;
import today.sesac.shoutify.post.exception.PostException;
import today.sesac.shoutify.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandService {
    private final PostRepository postRepository;
    // TODO PostService는  memberService만을 의존하고 member관련 오류는 memberService에선만 post관련은 PostService에서만 수행
    private final MemberService memberService;

    /**
     * 게시물 작성 api
     *
     * @param postCreateRequestDto 게시물 작성 dto
     * @param memberId             현재 로그인한 사용자의 id
     * @return PostCreateResponseDto 게시물 작성 dto
     */
    public PostCreateResponseDto savePost(PostCreateRequestDto postCreateRequestDto,
                                          Long memberId) {

        //1.작성자 정보 가져오기 (현재 사용자는 id=1로 하드코딩)
        Member author = memberService.getMember(memberId);
        //2.사용자가 작성한 원본내용 설정
        String beforeContents = postCreateRequestDto.getContent();
        //3.ai 처리된 afterContents 생성
        String afterContents = processAI(beforeContents);
        //감정선택하지 않았을 경우 ai 처리후 string값을 객체 값으로 전환
        //TODO ai 코드로 수정 예정
//        if (request.getEmotionType() == null) {
//            ai 감정 선택 코드 호출
//        }

        //정적 팩토리 메서드
        Post post = Post.createPost(
                author,
                beforeContents,
                afterContents,
                postCreateRequestDto.getTitle(),
                postCreateRequestDto.getImageUrl(),
                postCreateRequestDto.getEmotionType(),
                postCreateRequestDto.getConceptType()
        );

        Post savedPost = postRepository.save(post);

        return PostCreateResponseDto.of(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getAfterContent()
        );
    }

    /**
     * 게시물 삭제
     *
     * @param postId   삭제할 게시물의 id
     * @param memberId 현재 로그인한 사용자의 id
     */
    @Transactional
    public void deletePost(Long postId, Long memberId) {

        Post post = getPostById(postId);
        validatePostOwnership(post, memberId);
        //soft deleted
        post.delete();
        postRepository.save(post);
    }

    /**
     * 게시물 숨기기
     *
     * @param postId   숨길 게시물의 id
     * @param memberId 현재 로그인한 사용자의 id
     */
    @Transactional
    public void hidePost(Long postId, Long memberId) {
        Post post = getPostById(postId);
        validatePostOwnership(post, memberId);
        post.hide();
        postRepository.save(post);
    }

    /**
     * 게시물 숨김 해제
     *
     * @param postId   숨김 해제할
     * @param memberId 현재 로그인한 사용자의 id
     */
    @Transactional
    public void unhidePost(Long postId, Long memberId) {
        Post post = getPostById(postId);
        validatePostOwnership(post, memberId);
        post.unhide();
        postRepository.save(post);
    }

    /**
     * 내용 변환 ai 호출 임시 함수
     */
    private String processAI(String contents) {
        return contents;
    }


    /**
     * 작성자 권환 확인
     */
    private void validatePostOwnership(Post post, Long memberId) {
        if (!post.getAuthor().getId().equals(memberId)) {
            throw new PermissionRequiredException(memberId.toString(), "권한이 없습니다.");
        }
    }

    /**
     * 게시글 존재 여부 확인
     */
    private Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(
                        () -> new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString()));
    }
}
