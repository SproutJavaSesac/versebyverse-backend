package today.sesac.shoutify.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.shoutify.comment.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
