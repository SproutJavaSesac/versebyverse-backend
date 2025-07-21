package today.sesac.versebyverse.comment.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.comment.entity.Comment;

/**
 * 댓글 레포지토리 인터페이스.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 게시글 ID로 조회하고, path로 정렬해 댓글 목록을 페이지네이션 방식으로 조회합니다.
     *
     * @param postId   게시글 ID
     * @param pageable 페이지네이션 정보
     */
    @Query("""
            SELECT c FROM Comment c join fetch c.commenter commenter
            WHERE c.post.id = :postId ORDER BY c.path ASC
            """)
    Page<Comment> findByPostIdOrderByPathAsc(Long postId, Pageable pageable);

    /**
     * 게시글 ID로 삭제되지 않은 댓글을 조회합니다.
     *
     * @param postId 게시글 ID
     * @return 댓글이 존재하면 Optional에 포함된 Comment 객체, 없으면 Optional.empty()
     */
    Optional<Comment> findByIdAndIsDeletedFalse(Long postId);
}