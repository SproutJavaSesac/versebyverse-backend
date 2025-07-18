package today.sesac.versebyverse.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.exception.PostErrorCode;
import today.sesac.versebyverse.post.exception.PostException;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * 게시글 조회 service.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {
    private final PostRepository postRepository;

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

        return PostSingleQueryResponseDto.of(foundPost.getId(), foundPost.getAfterTitle(),
                foundPost.getAfterContent(), foundPost.getAuthor().getNickname(),
                foundPost.getCreatedAt(), foundPost.getImageUrl(),
                foundPost.getConceptType().toString(), foundPost.isMine(memberId));
    }

    /**
     * 게시글이 존재하는지 확인합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시글만 확인합니다.
     *
     * @param postId 게시글 ID
     * @throws PostException 게시글이 존재하지 않을 경우
     */
    public void validateActivePostByIdOrThrow(Long postId) {

        if (!postRepository.existsByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(postId)) {
            throw new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString());
        }
    }
}