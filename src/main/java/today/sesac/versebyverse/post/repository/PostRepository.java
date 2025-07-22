package today.sesac.versebyverse.post.repository;

import jakarta.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * 특정 기간 동안 작성된 게시글의 작성자별 게시글 수를 조회하고, 작성자별로 랭킹을 매깁니다.
     *
     * @param startDate 조회 시작 날짜
     * @param endDate   조회 종료 날짜
     * @return 작성자와 해당 작성자의 게시글 수, 랭킹 정보가 포함된 튜플 리스트
     */
    @Query(value = """
            SELECT p.author AS author,
            COUNT(p.id) AS postCount
            FROM Post p JOIN Member m ON p.author= m
            WHERE (p.createdAt BETWEEN :startDate AND :endDate)
            AND p.isDeleted = false And p.isHidden = false And p.isReported = false
            AND m.isDeleted = false
            GROUP BY p.author
            ORDER BY postCount DESC
            """)
    List<Tuple> findActiveAuthorActivePostCount(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    /**
     * 게시물 ID로 게시물을 조회합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물만 조회합니다.
     *
     * @param postId 게시물 ID
     * @return 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물의 Optional
     */
    Optional<Post> findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(Long postId);
}