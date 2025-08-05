package today.sesac.versebyverse.reaction.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.post.entity.Post;
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

    List<Reaction> post(Post post);

    /**
     * 게시글과 회원 id, 감정 타입을 기반으로 삭제할 emotion의 id 조회
     *
     * @param memberId
     * @param postId
     * @param emotion
     * @return 삭제할 id의 optional 객체
     */
    Optional<Reaction> findByMemberIdAndPostIdAndType(Long memberId, Long postId, Emotion emotion);

}
