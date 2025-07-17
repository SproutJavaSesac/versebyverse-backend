package today.sesac.versebyverse.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 repository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}