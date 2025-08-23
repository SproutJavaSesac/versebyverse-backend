package today.sesac.versebyverse.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.post.entity.PostProofreadAttempt;

@Repository
public interface PostProofreadAttemptRepository extends JpaRepository<PostProofreadAttempt, Long> {

}