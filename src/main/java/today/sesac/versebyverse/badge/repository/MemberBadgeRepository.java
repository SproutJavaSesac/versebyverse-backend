package today.sesac.versebyverse.badge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.versebyverse.badge.entity.MemberBadge;

/**
 * 회원의 배지 보유 여부를 저장하는 레포지토리.
 */
public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    /**
     * 특정 사용자가 보유한 총 배지를 조회합니다.
     *
     * @param memberId 사용자 ID
     * @return 사용자 배지 관계 목록
     */
    List<MemberBadge> findByMemberId(Long memberId);
}
