package today.sesac.shoutify.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.shoutify.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
