package today.sesac.shoutify.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import today.sesac.shoutify.post.entity.Post;

/**
 * 게시글 repository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //최신순 sort
    Page<Post> findByConceptType(String conceptType, Pageable pageable);

    //댓글순 sort
    @Query("""
            SELECT p FROM Post p
            LEFT JOIN Comment c ON p.id = c.post.id
            WHERE p.conceptType = :conceptType
            GROUP BY p
            ORDER BY COUNT(DISTINCT c.id) DESC
            """)
    Page<Post> findByConceptTypeOrderByCommentCount(String conceptType, Pageable pageable);

    //reaction 임시 주석 처리
//    //reaction sort
//    @Query("""
//            SELECT p FROM Post p LEFT JOIN Reaction r ON p.id = r.post.id \
//            WHERE p.conceptType = :conceptType \
//            GROUP BY p.id \
//            ORDER BY COUNT(r.id) DESC""")
//    Page<Post> findByConceptTypeOrderByReactionCount(String conceptType, Pageable pageable);
}
