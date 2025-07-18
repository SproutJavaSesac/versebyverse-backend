package today.sesac.versebyverse.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 repository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 게시글 ID로 게시글이 존재하는지 확인합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시글만 확인합니다.
     *
     * @param postId 게시글 ID
     * @return 존재하면 true, 아니면 false
     */
    boolean existsByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(Long postId);
}