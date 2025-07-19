package today.sesac.versebyverse.profanity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.versebyverse.profanity.entity.Profanity;

/**
 * 비속어 repository 인터페이스입니다.
 */
public interface ProfanityRepository extends JpaRepository<Profanity, Long> {

}