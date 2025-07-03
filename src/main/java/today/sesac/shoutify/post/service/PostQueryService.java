package today.sesac.shoutify.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.post.dto.response.PostSingleQueryResponseDto;
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

    /**
     * 게시글 단건 상세 조회.
     *
     * @param postId   게시글id
     * @param memberId 작성자id
     * @return postSingleQueryResponseDto
     */
    public PostSingleQueryResponseDto findById(Long postId, Long memberId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(
                () -> new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString()));

        return PostSingleQueryResponseDto.of(foundPost.getId(), foundPost.getAfterTitle(),
                foundPost.getAfterContent(), foundPost.getAuthor().getNickname(),
                foundPost.getCreatedAt(), foundPost.getImageUrl(),
                foundPost.getConceptType().toString(), foundPost.isMine(memberId));
    }
}
