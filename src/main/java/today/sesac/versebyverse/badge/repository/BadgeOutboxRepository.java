package today.sesac.versebyverse.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.versebyverse.badge.entity.BadgeOutbox;

/**
 * 배지 아웃박스 리포지토리.
 */
public interface BadgeOutboxRepository extends JpaRepository<BadgeOutbox, Long> {

}
