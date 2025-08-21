package today.sesac.versebyverse.badge.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.badge.entity.Badge;

/**
 * 배지 레포지토리 인터페이스.
 */
@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    /**
     * 배지 이름으로 배지를 조회합니다.
     *
     * @param name 배지의 이름
     * @return 배지의 Optional
     */
    Optional<Badge> findByName(String name);

}
