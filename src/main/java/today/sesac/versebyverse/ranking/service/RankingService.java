package today.sesac.versebyverse.ranking.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.ranking.entity.Ranking;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;
import today.sesac.versebyverse.ranking.repository.RankingRepository;
import today.sesac.versebyverse.ranking.response.RankingListResponseDto;
import today.sesac.versebyverse.ranking.response.RankingSingleResponseDto;
import today.sesac.versebyverse.ranking.util.DateTimeRange;
import today.sesac.versebyverse.ranking.util.DateTimeRangeCalculator;

/**
 * 순위(랭킹) 정보를 관리하는 서비스입니다. 다른 서비스에서 순위 정보를 조회할 때 사용됩니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    private static final String RANKING_NEW = "NEW";

    private static final String RANKING_UNCHANGED = "-";

    private static final String RANKING_UP = "+";

    /**
     * 특정 카테고리와 기간 유형에 해당하는 순위(랭킹) 정보를 조회합니다.
     *
     * @param category    조회할 순위(랭킹) 카테고리
     * @param periodType  조회할 순위(랭킹) 기간 유형
     * @param periodValue 조회할 순위(랭킹) 기간 값 (예: 특정 날짜)
     * @param pageable    페이징 정보
     * @return 해당 카테고리와 기간 유형에 해당하는 순위 목록
     */
    public RankingListResponseDto getRankingsByCategoryAndPeriod(RankingCategory category,
            RankingPeriodType periodType, LocalDate periodValue, Pageable pageable) {

        DateTimeRange periodDateTimeRange = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(
                periodValue,
                periodType);

        Page<Ranking> rankings = rankingRepository.findAllByCategoryAndPeriodTypeAndCreatedAtBetween(
                category, periodType, periodDateTimeRange.startDateTime(),
                periodDateTimeRange.endDateTime(),
                pageable);

        List<RankingSingleResponseDto> rankingDtoList = rankings.stream()
                .map(ranking -> new RankingSingleResponseDto(
                        ranking,
                        getRankChangeWithSymbol(ranking.getRanks(), ranking.getPreviousRank())
                )).toList();

        return new RankingListResponseDto(category, periodType, periodValue, rankingDtoList);
    }

    private String getRankChangeWithSymbol(int rank, Integer previousRank) {

        if (previousRank == null) {
            return RANKING_NEW;
        } else if (rank == previousRank) {
            return RANKING_UNCHANGED;
        } else {
            return previousRank - rank > 0
                    ? RANKING_UP + (previousRank - rank)
                    : String.valueOf(previousRank - rank);
        }
    }

    /**
     * 내 순위(랭킹) 정보를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 내 순위(랭킹) 정보
     */
    public Ranking getMyRankingByMemberId(Long memberId) {

        return null;
    }

}