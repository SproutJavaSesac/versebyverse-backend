package today.sesac.shoutify.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.post.entity.Post;

/**
 * 게시글 repository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //전체 조회 댓글순 sort
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
//    Page<Post> findAllOrderByReactionCount(Pageable pageable);


    //컨셉별 최신순 sort
    @Query("""
            SELECT p FROM Post p 
            WHERE p.conceptType = :conceptType
            """)
    Page<Post> findByConceptType(Concept conceptType, Pageable pageable);

    //컨셉별 댓글순 sort
    @Query("""
            SELECT p FROM Post p
            LEFT JOIN Comment c ON p.id = c.post.id
            WHERE p.conceptType = :conceptType
            GROUP BY p
            ORDER BY COUNT(DISTINCT c.id) DESC
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
}
