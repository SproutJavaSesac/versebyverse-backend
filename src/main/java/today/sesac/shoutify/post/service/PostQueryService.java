package today.sesac.shoutify.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.comment.repository.CommentRepository;
import today.sesac.shoutify.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.shoutify.post.dto.response.PostSummaryResponseDto;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.exception.PostErrorCode;
import today.sesac.shoutify.post.exception.PostException;
import today.sesac.shoutify.post.repository.PostRepository;

/**
 * 게시글 조회 service.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /**
     * 게시글 목록 리스트 조회.
     */
    public Page<PostSummaryResponseDto> getPosts(String conceptType, String sortBy,
                                                 int page, int size) {

        //jpa 에게 요청하는 데이터 양식, 최신순 정렬이 기본
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        //repository를 통해 jpa 가 db 에서 받아온 post 엔티티 + 페이지 정보
        Page<Post> postPage;

        if (conceptType == null) {
            //전체 조회 (무한 스크롤)
            postPage = postRepository.findAll(pageable);
        } else {
            switch (sortBy) {
                //댓글 많은 순 정렬
                case "comments":
                    postPage = postRepository.findByConceptTypeOrderByCommentCount(conceptType,
                            pageable);
                    break;
                //반응 많은 순 정렬
                case "reactions":
                    //TODO 반응하기 임시 기본 정렬 대체
                    postPage = postRepository.findByConceptType(conceptType, pageable);
//                    postPage = postRepository.findByConceptTypeOrderByReactionCount(conceptType,
//                            pageable);
                    break;
                //기본 최신순 정렬
                default: // 최신순
                    postPage = postRepository.findByConceptType(conceptType, pageable);
            }
        }
        //Post 객체를 dto객체로 변환
        return postPage.map(post -> {
            int commentCount = commentRepository.countByPostId(post.getId());
            // TODO reaction개수 임시 0으로 고정
            int reactionCount = 0;
            //int reactionCount = reactionRepository.countByPostId(post.getId());
            return PostSummaryResponseDto.of(post, commentCount, reactionCount);
        });
    }


    /**
     * 게시글 단건 상세 조회.
     *
     * @param postId   게시글id
     * @param memberId 작성자id
     * @return postSingleQueryResponseDto
     */
    public PostSingleQueryResponseDto getPostDetail(Long postId, Long memberId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(
                () -> new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString()));
        // TODO 댓글갯수,반응갯수, 댓글 총갯수 추가
        return PostSingleQueryResponseDto.of(foundPost.getId(), foundPost.getAfterTitle(),
                foundPost.getAfterContent(), foundPost.getAuthor().getNickname(),
                foundPost.getCreatedAt(), foundPost.getImageUrl(),
                foundPost.getConceptType().toString(), foundPost.isMine(memberId));
    }
}
