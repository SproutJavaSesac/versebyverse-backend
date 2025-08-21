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

    /**
     * 댓글 작성자의 id로 작성자가 작성한 댓글의 목록을 페이지로 가져옵니다. 삭제된 댓글은 포함하지 않습니다.
     *
     * @param commenterId 댓글 작성자 ID
     * @param pageable    페이지네이션 정보
     * @return 페이지로 가져온 작성자 댓글 목록
     */
    Page<Comment> findByCommenterIdAndIsDeletedFalseOrderByCreatedAtDesc(Long commenterId,
            Pageable pageable);

    /**
     * 사용자가 작성한 총 댓글의 수를 조회합니다. 삭제된 댓글은 포함하지 않습니다.
     *
     * @param commenterId 댓글 작성자 ID
     * @return 사용자가 작성한 총 댓글의 수
     */
    int countByCommenterIdAndIsDeletedFalse(Long commenterId);

    /**
     * 게시글 id로 해당 게시글의 삭제되지 않고, 신고되지 않은 댓글 수를 조회합니다.
     *
     * @param id 게시글 ID
     * @return Long 으로 해당 게시글의 댓글 수
     */
    Long countByPostIdAndIsDeletedFalseAndIsBlockedFalse(Long id);

    /**
     * 게시글 id로 해당 게시글의 댓글 수를 조회합니다.
     *
     * @param postId 게시글 ID
     * @return 게시글의 댓글 수
     */
    int countByPostIdAndIsDeletedFalse(Long postId);

    /**
     * 댓글 작성자의 id로 작성자가 작성한 댓글의 목록을 페이지로 가져옵니다.
     * 차단된 댓글, 삭제된 댓글은 포함하지 않습니다.
     *
     * @param commenterId 댓글 작성자 ID
     * @param pageable 페이지네이션 정보
     * @return 페이지로 가져온 작성자 댓글 목록
     */
    Page<Comment> findByCommenterIdAndIsDeletedFalseAndIsBlockedFalseOrderByCreatedAtDesc(Long commenterId, Pageable pageable);


    /**
     * 댓글이 삭제되지 않았거나 관리자로부터 신고 승인되지 않은 댓글을 조회합니다.
     *
     * @param commentId 댓글 ID
     * @return 조회한 댓글
     */
    Optional<Comment> findByIdAndIsDeletedFalseAndIsBlockedFalse(Long commentId);

    /**
     * 특정 댓글을 게시글 ID와 함께 조회합니다.
     *
     * @param commentId 댓글 ID
     * @param postId    게시글 ID
     * @return {@code Optional<Comment>} 댓글이 존재하면 Optional에 포함된 Comment 객체, 없으면 Optional.empty()
     */
    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);
}