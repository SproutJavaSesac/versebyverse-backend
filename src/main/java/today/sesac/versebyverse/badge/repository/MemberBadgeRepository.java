package today.sesac.versebyverse.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.versebyverse.badge.entity.MemberBadge;

/**
 * 회원의 배지 보유 여부를 저장하는 레포지토리.
 */
public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

}
