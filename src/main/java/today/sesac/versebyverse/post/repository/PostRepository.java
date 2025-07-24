package today.sesac.versebyverse.post.repository;

import jakarta.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 repository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 게시물 전체 조회 + 최신순 기본 정렬
     *
     * @param pageable 페이지네이션 정보
     */
    @Query("""
            SELECT p FROM Post p
            ORDER BY p.createdAt DESC
            """)
    Page<Post> findAllOrderByCreatedAt(Pageable pageable);


    /**
     * 게시물 전체 조회 + 댓글 순 정렬.
     *
     * @param pageable 페이지네이션 정보
     */
    @Query("""
            SELECT p FROM Post p LEFT JOIN Comment c ON p.id = c.post.id
            GROUP BY p.id
            ORDER BY COUNT(c.id) DESC
            """)
    Page<Post> findAllOrderByCommentCount(Pageable pageable);

    // TODO reaction 임시 주석 처리
    //전체 조회 reaction순 sort
//    @Query("""
//    SELECT p FROM Post p LEFT JOIN Reaction r ON p.id = r.post.id
//    GROUP BY p.id
//    ORDER BY COUNT(r.id) DESC
//      """)
//Page<Post> findAllOrderByReactionCount(Pageable pageable);


    /**
     * 컨셉별 조회 + 최신순 정렬.
     *
     * @param conceptType 컨셉 타입
     * @param pageable    페이지네이션 정보
     */
    @Query("""
            SELECT p FROM Post p 
            WHERE p.conceptType = :conceptType
            """)
    Page<Post> findByConceptType(Concept conceptType, Pageable pageable);

    /**
     * 컨셉별 조회 + 댓글순 정렬.
     *
     * @param conceptType 컨셉 타입
     * @param pageable    페이지네이션 정보
     */
    @Query("""
            SELECT p FROM Post p
            LEFT JOIN Comment c ON p.id = c.post.id
            WHERE p.conceptType = :conceptType
            GROUP BY p
            ORDER BY COUNT(c.id) DESC
            """)
    Page<Post> findByConceptTypeOrderByCommentCount(Concept conceptType, Pageable pageable);

    // TODO reaction 임시 주석 처리
//    //컨셉별 reaction sort
//    @Query("""
//            SELECT p FROM Post p LEFT JOIN Reaction r ON p.id = r.post.id \
//            WHERE p.conceptType = :conceptType \
//            GROUP BY p.id \
//            ORDER BY COUNT(r.id) DESC""")
//    Page<Post> findByConceptTypeOrderByReactionCount(Concept conceptType, Pageable pageable);

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
