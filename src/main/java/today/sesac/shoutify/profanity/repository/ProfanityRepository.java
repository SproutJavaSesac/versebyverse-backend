package today.sesac.shoutify.profanity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.shoutify.profanity.entity.Profanity;

public interface ProfanityRepository extends JpaRepository<Profanity, Long> {

}
