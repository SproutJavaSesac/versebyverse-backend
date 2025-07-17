package today.sesac.versebyverse.ranking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.dto.AuthorPostCountDto;
import today.sesac.versebyverse.post.service.PostQueryService;
import today.sesac.versebyverse.ranking.dto.response.RankingListResponseDto;
import today.sesac.versebyverse.ranking.dto.response.RankingSingleResponseDto;
import today.sesac.versebyverse.ranking.entity.Ranking;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;
import today.sesac.versebyverse.ranking.repository.RankingRepository;
import today.sesac.versebyverse.ranking.util.DateTimeRange;
import today.sesac.versebyverse.ranking.util.DateTimeRangeCalculator;

/**
 * 순위(랭킹) 정보를 관리하는 서비스입니다. 다른 서비스에서 순위 정보를 조회할 때 사용됩니다.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankingService {

    private static final String RANKING_NEW = "NEW";

    private static final String RANKING_UNCHANGED = "-";

    private static final String RANKING_UP = "+";

    private final RankingRepository rankingRepository;

    private final PostQueryService postQueryService;

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

        Page<Ranking> rankings =
                rankingRepository.findAllByCategoryAndPeriodTypeAndCreatedAtBetween(
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
     * 일정 기간 단위로 순위(랭킹)를 계산합니다. 해당 기간 동안의 게시글 수를 기준으로 회원의 순위를 계산합니다.
     *
     * @param startDateTime 시작 날짜 및 시간
     * @param endDateTime   종료 날짜 및 시간
     * @param periodType    순위(랭킹) 기간 유형 (예: 일간, 주간 등)
     */
    @Transactional
    public void calculatePostsRanking(LocalDateTime startDateTime, LocalDateTime endDateTime,
            RankingPeriodType periodType) {

        List<AuthorPostCountDto> authorAndPostCountList = postQueryService.getAuthorAndPostCount(
                startDateTime, endDateTime);

        int lastProcessedPostCount = 0;
        int rank = 0;
        for (int i = 0; i < authorAndPostCountList.size(); i++) {

            // TODO 여기서 null이 나오는 경우 확인 필요
            Member member = authorAndPostCountList.get(i).author();
            // TODO null, long -> int 예외 확인 필요
            int postCount = authorAndPostCountList.get(i).postCount().intValue();
            if (i == 0 || lastProcessedPostCount != postCount) {
                rank = i + 1;
            }

            // 어제 날짜로 createdAt에서 어제 날짜, member, category 조회 후 없으면 새로 저장, 있으면 update
            saveRankingNewOrWithPrevious(member, postCount, rank, startDateTime, endDateTime,
                    periodType);

            lastProcessedPostCount = postCount;
        }
    }

    private void saveRankingNewOrWithPrevious(Member member, int postCount, int rank,
            LocalDateTime startDateTime, LocalDateTime endDateTime, RankingPeriodType periodType) {

        Optional<Ranking> existingRanking =
                rankingRepository.findByMemberIdAndCategoryAndPeriodTypeAndCreatedAtBetween(
                        member.getId(), RankingCategory.POST,
                        periodType,
                        startDateTime, endDateTime
                );

        Ranking ranking = existingRanking
                .map(existing -> createRankingWithPreviousRank(
                        member, postCount, rank, existing.getRanks(), periodType))
                .orElse(createNewRanking(member, postCount, rank, periodType));

        rankingRepository.save(ranking);
    }

    private Ranking createNewRanking(Member member, int postCount, int rank,
            RankingPeriodType periodType) {

        return Ranking.createFirstEntry(
                member,
                RankingCategory.POST,
                postCount,
                rank,
                periodType
        );
    }

    private Ranking createRankingWithPreviousRank(Member member, int postCount, int rank,
            int previousRank, RankingPeriodType periodType) {
        // 업데이트
        return Ranking.createWithPreviousRank(
                member,
                RankingCategory.POST,
                postCount,
                rank,
                previousRank,
                periodType
        );
    }
}