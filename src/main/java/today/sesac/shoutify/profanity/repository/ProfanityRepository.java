package today.sesac.shoutify.profanity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.shoutify.profanity.entity.Profanity;

/**
 * 비속어 repository 인터페이스입니다.
 */
public interface ProfanityRepository extends JpaRepository<Profanity, Long> {

}
