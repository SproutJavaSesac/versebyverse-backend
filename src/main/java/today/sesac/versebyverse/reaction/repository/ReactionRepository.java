package today.sesac.versebyverse.reaction.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.reaction.entity.Reaction;

/**
 * 반응하기 repository.
 */
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
    List<Object[]> countReactionsByTypeForPost(@Param("postId") Long postId);

    /**
     * 댓글 id에 대한 반응별 갯수 조회.
     *
     * @param commentId 댓글 id
     * @return 반응 type별 갯수
     */
    @Query("""
                    select r.type, count(r) from Reaction  r where r.comment.id = :commentId group by r.type
            """)
    List<Object[]> countReactionsByTypeForComment(@Param("commentId") Long commentId);

    /**
     * 회원이 특정 게시글에 누른 반응을 조회.
     *
     * @param memberId 회원 id
     * @param postId   게시글 id
     * @param emotion  감정
     */
    Optional<Reaction> findByMemberIdAndPostIdAndType(Long memberId, Long postId, Emotion emotion);

    /**
     * 댓글 id, 회원 id , 감정을 기반으로 reaction의 id 조회.
     *
     * @param memberId  회원 id
     * @param commentId 댓글 id
     * @param emotion   감정
     */
    Optional<Reaction> findByMemberIdAndCommentIdAndType(Long memberId, Long commentId,
            Emotion emotion);

    /**
     * 회원 id, 게시글 id로 reaction 조회.
     *
     * @param memberId 회원 id
     * @param targetId 게시글 id
     */
    Optional<Reaction> findByMemberIdAndPostId(Long memberId, Long targetId);

    /**
     * 회원 id, 댓글 id로 reaction 조회.
     *
     * @param memberId 회원 id
     * @param targetId 댓글 id
     */
    Optional<Reaction> findByMemberIdAndCommentId(Long memberId, Long targetId);

    /**
     * 게시글의 id로 게시글 총 반응 갯수 조회.
     *
     * @param postId 게시글 id
     */
    int countByPostId(@Param("postId") Long postId);

    /**
     * 여러 댓글의 리액션을 한 번에 조회.
     *
     * @param commentIds 댓글 ID 리스트
     * @return [감정타입, 개수] 형태의 Object 배열 리스트
     */
    @Query("""
              SELECT r.type, COUNT(r), r.comment.id 
              FROM Reaction r 
              WHERE r.comment.id IN :commentIds 
              GROUP BY r.comment.id, r.type
            """)
    List<Object[]> countReactionsByTypeForMultipleComments(
            @Param("commentIds") List<Long> commentIds);
}
