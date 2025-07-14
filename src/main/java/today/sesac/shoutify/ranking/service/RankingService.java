package today.sesac.shoutify.ranking.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.ranking.dto.response.RankingListResponseDto;
import today.sesac.shoutify.ranking.dto.response.RankingSingleResponseDto;
import today.sesac.shoutify.ranking.entity.Ranking;
import today.sesac.shoutify.ranking.entity.RankingCategory;
import today.sesac.shoutify.ranking.entity.RankingPeriodType;
import today.sesac.shoutify.ranking.repository.RankingRepository;
import today.sesac.shoutify.ranking.util.DateDuration;
import today.sesac.shoutify.ranking.util.TimeCalculator;

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
     * 순위(랭킹) 정보를 조회합니다.
     *
     * @param category 조회할 순위(랭킹) 카테고리
     */
    public RankingListResponseDto getRankingsByCategory(RankingCategory category,
            RankingPeriodType periodType, LocalDate periodValue, Pageable pageable) {

        DateDuration dateDuration = TimeCalculator.getStartDateAndEndDateByDuration(periodValue,
                periodType);

        Page<Ranking> rankings = rankingRepository.findAllByCategoryAndPeriodTypeAndCreatedAtBetween(
                category, periodType, dateDuration.startDateTime(), dateDuration.endDateTime(),
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
            return rank - previousRank > 0
                    ? RANKING_UP + (rank - previousRank)
                    : String.valueOf(rank - previousRank);
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