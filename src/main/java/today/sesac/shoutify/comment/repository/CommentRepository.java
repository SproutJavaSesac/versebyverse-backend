package today.sesac.shoutify.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import today.sesac.shoutify.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(Long postId);

	@Modifying
	@Query("UPDATE Comment c SET c.isDeleted = true WHERE c.id = :id")
	int softDeleteById(@Param("id") Long id);
}
