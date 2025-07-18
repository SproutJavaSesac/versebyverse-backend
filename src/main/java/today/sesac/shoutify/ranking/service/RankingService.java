package today.sesac.versebyverse.ranking.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.member.dto.response.MyRankingListResponseDto;
import today.sesac.versebyverse.member.dto.response.MyRankingSummary;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.ranking.entity.Ranking;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;
import today.sesac.versebyverse.ranking.repository.RankingRepository;

/**
 * 순위(랭킹) 정보를 관리하는 서비스입니다. 다른 서비스에서 순위 정보를 조회할 때 사용됩니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    private final MemberService memberService;

    /**
     * 순위(랭킹) 정보를 조회합니다.
     * TODO 추후 RankingResponse로 변경 예정
     *
     * @param category 조회할 순위(랭킹) 카테고리
     */
    public List<Ranking> getRankingsByCategory(RankingCategory category) {

        return List.of();
    }

    /**
     * 특정 회원의 순위(랭킹) 정보를 조회합니다.
     *
     * @param memberId   회원 ID
     * @param category   조회할 순위(랭킹) 카테고리
     * @param periodType 조회할 기간 타입
     * @param maxCount   조회하는 랭킹 최대 개수(최대 30개)
     * @return 해당 회원의 순위(랭킹) 정보
     */
    public MyRankingListResponseDto getMyRankingByMemberId(Long memberId, RankingCategory category,
            RankingPeriodType periodType, int maxCount) {

        memberService.validateMemberActiveExists(memberId);

        LocalDateTime endDateTime = LocalDateTime.now();
        List<MyRankingSummary> myRankingSummaryList = rankingRepository
                .findAllByMemberIdAndCategoryAndPeriodTypeAndCreatedAtBetween(
                        memberId, category, periodType,
                        getStartTimeFromEndTimeAndPeriod(
                                endDateTime, periodType, maxCount),
                        endDateTime)
                .stream()
                .map(ranking -> {
                    String rankChange = getRankChangeWithSymbol(ranking.getRanks(),
                            ranking.getPreviousRank());
                    return new MyRankingSummary(ranking, rankChange);
                }).toList();

        return new MyRankingListResponseDto(
                category, maxCount, periodType, myRankingSummaryList
        );
    }

    // todo periodType 추가 시 수정 필요
    private LocalDateTime getStartTimeFromEndTimeAndPeriod(LocalDateTime endDateTime,
            RankingPeriodType periodType, int maxCount) {

        return switch (periodType) {
            case DAILY -> endDateTime.minusDays(maxCount - 1L);
        };
    }

}