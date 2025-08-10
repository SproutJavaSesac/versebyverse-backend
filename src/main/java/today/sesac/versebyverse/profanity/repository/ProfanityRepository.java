package today.sesac.versebyverse.profanity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.versebyverse.profanity.entity.Profanity;

/**
 * 비속어 repository 인터페이스입니다.
 */
public interface ProfanityRepository extends JpaRepository<Profanity, Long> {

    /**
     * 비속어 수정시 변경 할 original의 중복 체크를 합니다.
     *
     * @param original 비속어 원문
     * @return original 존재 여부
     */
    boolean existsByOriginal(String original);

}