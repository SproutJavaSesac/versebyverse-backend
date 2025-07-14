package today.sesac.shoutify.ranking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.post.dto.AuthorPostStatDto;
import today.sesac.shoutify.post.service.PostQueryService;
import today.sesac.shoutify.ranking.entity.Ranking;
import today.sesac.shoutify.ranking.entity.RankingCategory;
import today.sesac.shoutify.ranking.entity.RankingPeriodType;
import today.sesac.shoutify.ranking.repository.RankingRepository;

/**
 * 순위(랭킹) 정보를 관리하는 서비스입니다. 다른 서비스에서 순위 정보를 조회할 때 사용됩니다.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    private final PostQueryService postQueryService;

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
     * 내 순위(랭킹) 정보를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 내 순위(랭킹) 정보
     */
    public Ranking getMyRankingByMemberId(Long memberId) {

        return null;
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

        List<AuthorPostStatDto> authorAndPostCounts = postQueryService.getAuthorAndPostCounts(
                startDateTime, endDateTime);

        int lastProcessedPostCount = 0;
        int rank = 0;
        for (int i = 0; i < authorAndPostCounts.size(); i++) {

            // TODO 여기서 null이 나오는 경우 확인 필요
            Member member = authorAndPostCounts.get(i).author();
            // TODO null, long -> int 예외 확인 필요
            int postCount = authorAndPostCounts.get(i).postCount().intValue();
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

        Optional<Ranking> existingRanking = rankingRepository.findByMemberAndCategoryAndPeriodTypeAndCreatedAtBetween(
                member, RankingCategory.POST,
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
