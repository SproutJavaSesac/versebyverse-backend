package today.sesac.versebyverse.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.post.entity.PostProofreadAttempt;

/**
 * PostProofreadAttempt 엔티티에 대한 JPA 리포지토리입니다.
 */
@Repository
public interface PostProofreadAttemptRepository extends JpaRepository<PostProofreadAttempt, Long> {

}