package today.sesac.versebyverse.ranking.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.ranking.entity.Ranking;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;

/**
 * 순위(랭킹) 정보를 관리하는 레포지토리입니다. 기본적으로 순위 서비스에서 사용됩니다.
 */
@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    /**
     * 특정 회원의 순위(랭킹)를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return {@code Optional<Ranking>} 해당 회원의 순위 정보.
     */
    Optional<Ranking> findByMemberIdAndCategoryAndPeriodTypeAndCreatedAtBetween(
            Long memberId,
            RankingCategory category,
            RankingPeriodType rankingPeriodType,
            LocalDateTime start,
            LocalDateTime end
    );
}