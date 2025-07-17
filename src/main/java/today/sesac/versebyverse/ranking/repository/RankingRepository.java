package today.sesac.versebyverse.ranking.repository;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 특정 카테고리와 기간 유형에 해당하는 순위(랭킹) 정보를 조회합니다.
     *
     * @param category      조회할 순위(랭킹) 카테고리
     * @param periodType    조회할 순위(랭킹) 기간 유형
     * @param startDateTime 시작 날짜 및 시간
     * @param endDateTime   종료 날짜 및 시간
     * @param pageable      페이징 정보
     * @return 해당 조건에 맞는 순위(랭킹) 정보의 페이지
     */
    Page<Ranking> findAllByCategoryAndPeriodTypeAndCreatedAtBetween(
            RankingCategory category, RankingPeriodType periodType, LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Pageable pageable);
}