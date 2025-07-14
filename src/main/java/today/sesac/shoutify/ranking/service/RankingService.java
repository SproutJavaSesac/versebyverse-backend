package today.sesac.shoutify.ranking.service;

import java.time.LocalDate;
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
     * 하루 단위로 순위(랭킹)를 계산합니다. 어제 하루 동안의 게시글 수를 기준으로 회원의 순위를 계산합니다.
     */
    @Transactional
    public void calculatePostsRankingPerDay() {

        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime yesterdayStartTime = yesterday.atStartOfDay();        // 어제 00시
        LocalDateTime yesterdayEndTime = yesterdayStartTime.plusDays(1)
                .minusSeconds(1);        // 어제 23시 59분 59초

        List<AuthorPostStatDto> authorAndPostCounts = postQueryService.getAuthorAndPostCounts(
                yesterdayStartTime, yesterdayEndTime);

        int previousPostCount = 0;
        int rank = 0;
        for (int i = 0; i < authorAndPostCounts.size(); i++) {

            // TODO 여기서 null이 나오는 경우 확인 필요
            Member member = authorAndPostCounts.get(i).author();
            // TODO null, long -> int 예외 확인 필요
            int postCount = authorAndPostCounts.get(i).postCount().intValue();
            if (i == 0 || previousPostCount != postCount) {
                rank = i + 1;
            }

            // 어제 날짜로 createdAt에서 어제 날짜, member, category 조회 후 없으면 새로 저장, 있으면 update
            Optional<Ranking> existingRankingOptinal =
                    rankingRepository.findByMemberAndCategoryAndPeriodTypeAndCreatedAtBetween(
                            member, RankingCategory.POST,
                            RankingPeriodType.DAILY,
                            yesterdayStartTime,
                            yesterdayEndTime
                    );

            Ranking ranking;
            boolean isNewRanking = existingRankingOptinal.isEmpty();
            if (isNewRanking) {
                ranking = createNewRanking(member, postCount, rank);
                previousPostCount = postCount;
            } else {
                ranking = createRankingWithPreviousRank(member, postCount, rank,
                        existingRankingOptinal.get().getPreviousRank());
            }
            rankingRepository.save(ranking);
        }
    }

    private Ranking createNewRanking(Member member, int postCount, int rank) {

        return Ranking.createFirstEntry(
                member,
                RankingCategory.POST,
                postCount,
                rank,
                RankingPeriodType.DAILY
        );
    }

    private Ranking createRankingWithPreviousRank(Member member, int postCount, int rank,
            int previousRank) {
        // 업데이트
        return Ranking.createWithPreviousRank(
                member,
                RankingCategory.POST,
                postCount,
                rank,
                previousRank,
                RankingPeriodType.DAILY
        );
    }
}
