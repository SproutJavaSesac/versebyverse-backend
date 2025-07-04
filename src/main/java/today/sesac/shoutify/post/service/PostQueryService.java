package today.sesac.shoutify.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.exception.PostErrorCode;
import today.sesac.shoutify.post.exception.PostException;
import today.sesac.shoutify.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostRepository postRepository;

    /**
     * 게시물 ID로 게시물을 조회합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물만 조회합니다.
     *
     * @param postId 게시물 ID
     * @return 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물 Entity 객체
     */
    public Post getActivePostById(Long postId) {

        return postRepository
                .findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_FOUND, "postId"));
    }
}
