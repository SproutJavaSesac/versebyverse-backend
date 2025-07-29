package today.sesac.versebyverse.profanity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.versebyverse.profanity.entity.Profanity;

public interface ProfanityRepository extends JpaRepository<Profanity, Long> {

    boolean existsByOriginal(String original);

}