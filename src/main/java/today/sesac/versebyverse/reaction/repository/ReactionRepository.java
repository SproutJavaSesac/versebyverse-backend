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
    List<Object[]> countReactionsByPostId(@Param("postId") Long postId);

    /**
     * 댓글 id에 대한 반응별 갯수 조회.
     *
     * @param commentId 댓글 id
     * @return 반응 type별 갯수
     */
    @Query("""
                    select r.type, count(r) from Reaction  r where r.comment.id = :commentId group by r.type
            """)
    List<Object[]> countReactionsByCommentId(@Param("commentId") Long commentId);

    List<Reaction> post(Post post);

    /**
     * 게시글 id, 회원 id, 감정을 기반으로 reaction의 id 조회.
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
     * 사용자가 게시글에 한 모든 반응 조회
     *
     * @param memberId
     * @param postId
     */
    Optional<Reaction> findByMember_IdAndPost_Id(Long memberId, Long postId);

    /**
     * 사용자가 댓글에 한 모든 반응 조회
     *
     * @param memberId
     * @param targetId
     */
    Optional<Reaction> findByMember_IdAndComment_Id(Long memberId, Long targetId);
}
