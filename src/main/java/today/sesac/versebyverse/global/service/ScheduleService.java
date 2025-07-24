package today.sesac.versebyverse.global.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;
import today.sesac.versebyverse.ranking.service.RankingService;

/**
 * 스케줄링 서비스입니다. 주기적으로 실행되는 작업을 관리합니다.
 * todo async, transactional new 고민 필요.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final RankingService rankingService;

    /**
     * 매일 자정에 어제의 게시글 순위를 계산하고, 저장하는 스케줄링 작업입니다.
     *
     * @see org.springframework.scheduling.support.CronExpression
     */
    @Scheduled(cron = "@midnight") // 매일 자정에 실행
    @Transactional
    public void scheduleCalculateYesterdayPostRanking() {
        // todo timecaclulator로 계산 필요
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime yesterdayStartTime = yesterday.atStartOfDay();        // 어제 00시
        LocalDateTime yesterdayEndTime = yesterdayStartTime.plusDays(1)
                .minusNanos(1);        // 어제 23시 59분 59초
        rankingService.calculatePostsRanking(yesterdayStartTime, yesterdayEndTime,
                RankingPeriodType.DAILY);
    }
}