package today.sesac.versebyverse.profanity.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import today.sesac.versebyverse.profanity.entity.Profanity;

/**
 * 비속어 repository 인터페이스입니다.
 */
public interface ProfanityRepository extends JpaRepository<Profanity, Long> {

    /**
     * 비속어(original 필드)의 중복 여부 확인합니다.
     */
    boolean existsByOriginal(String original);

    /**
     * DELETE 쿼리를 실행하며 삭제된 행의 수를 반환합니다.
     *
     * @param profanityId 삭제할 비속어 ID
     * @return 삭제된 행의 수 (성공 1, 존재하지 않을 시 0)
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Profanity p WHERE p.id = :profanityId")
    int deleteByIdIfExists(long profanityId);
}