package today.sesac.versebyverse.reaction.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.reaction.entity.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    /**
     * 게시글 id에 대한 반응별 갯수 조회.
     *
     * @param postId 게시글 id
     * @return 반응 type별 갯수
     */
    @Query("""
                    select r.type, count(r) from Reaction r where r.post.id = :postId group by r.type
            """)
    List<Object[]> countReactionsByPostId(@Param("postId") Long postId);

    /**
     * 댓글 id에 대한 반응별 갯수 조회
     *
     * @param commentId
     * @return 반응 type별 갯수
     */
    @Query("""
                    select r.type, count(r) from Reaction  r where r.comment.id = :commentId group by r.type
            """)
    List<Object[]> countReactionsByCommentId(@Param("commentId") Long commentId);


}
