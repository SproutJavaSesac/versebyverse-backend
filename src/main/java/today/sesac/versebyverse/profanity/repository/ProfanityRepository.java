package today.sesac.versebyverse.profanity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import today.sesac.versebyverse.profanity.entity.Profanity;

/**
 * 비속어 repository 인터페이스입니다.
 */
public interface ProfanityRepository extends JpaRepository<Profanity, Long> {

    /**
     * 비속어 수정시 변경 할 original의 중복 체크를 합니다.
     *
     * @param original 비속어 원문
     * @return original 존재 여
     */
    boolean existsByOriginal(String original);

    /**
     * DELETE 쿼리를 실행하며 삭제된 행의 수를 반환합니다.
     *
     * @param profanityId 삭제할 비속어 ID
     * @return 삭제된 행의 수 (성공 1, 존재하지 않을 시 0)
     */
    @Modifying
    @Query("DELETE FROM Profanity p WHERE p.id = :profanityId")
    int deleteByIdIfExists(long profanityId);


    @Query("SELECT p FROM Profanity p WHERE " +
            "LOWER(p.original) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.replacement) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Profanity> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}