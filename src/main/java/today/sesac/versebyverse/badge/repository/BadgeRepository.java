package today.sesac.versebyverse.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.badge.entity.Badge;

/**
 * 배지 레포지토리 인터페이스.
 */
@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
