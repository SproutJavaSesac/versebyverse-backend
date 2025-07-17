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
                        getRankChangeWithSymbol(ranking.getRank(), ranking.getPreviousRank())
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

        List<AuthorPostCountDto> authorPostCountList = postQueryService
                .getAuthorAndPostCount(startDateTime, endDateTime);

        processRankingCalculation(authorPostCountList, startDateTime, endDateTime, periodType);
    }

    /**
     * 순위 계산 처리를 담당하는 메서드입니다. 불변 데이터와 순수 함수를 사용하여 동시성 안전성을 보장합니다.
     */
    private void processRankingCalculation(List<AuthorPostCountDto> authorPostCountList,
            LocalDateTime startDateTime, LocalDateTime endDateTime, RankingPeriodType periodType) {

        int lastProcessedPostCount = 0;
        int currentRank = 0;

        for (int i = 0; i < authorPostCountList.size(); i++) {
            AuthorPostCountDto authorPostCount = authorPostCountList.get(i);

            // TODO 여기서 null이 나오는 경우 확인 필요
            Member member = authorPostCount.author();
            // TODO null, long -> int 예외 확인 필요
            int postCount = authorPostCount.postCount().intValue();

            currentRank = calculateRank(i, postCount, lastProcessedPostCount, currentRank);

            saveRankingNewOrWithPrevious(member, postCount, currentRank, startDateTime, endDateTime,
                    periodType);

            lastProcessedPostCount = postCount;
        }
    }

    /**
     * 현재 사용자의 순위를 계산하는 순수 함수입니다.
     * 동점자의 경우 같은 순위를 부여하고, 다음 순위는 실제 순서를 반영합니다.
     * 예: 1위(10개), 2위(8개), 2위(8개), 4위(7개)
     * TODO 추후 클래스 분리 및 테스트 코드 작성 필요
     *
     * @param currentIndex 현재 처리 중인 사용자의 인덱스 (0부터 시작)
     * @param currentPostCount 현재 사용자의 게시글 수
     * @param previousPostCount 이전에 처리한 사용자의 게시글 수
     * @param previousRank 이전에 처리한 사용자의 순위
     * @return 현재 사용자의 순위
     */
    private int calculateRank(int currentIndex, int currentPostCount,
            int previousPostCount, int previousRank) {

        // 첫 번째 사용자이거나 이전 사용자와 게시글 수가 다른 경우: 새로운 순위 부여
        if (currentIndex == 0 || previousPostCount != currentPostCount) {
            return currentIndex + 1;
        }

        // 이전 사용자와 게시글 수가 같은 경우: 동점자로 같은 순위 유지
        return previousRank;
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
                        member, postCount, rank, existing.getRank(), periodType))
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