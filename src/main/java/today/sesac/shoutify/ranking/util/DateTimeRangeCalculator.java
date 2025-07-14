package today.sesac.shoutify.ranking.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import today.sesac.shoutify.ranking.entity.RankingPeriodType;

/**
 * 시간 계산을 위한 유틸리티 클래스.
 */
public final class DateTimeRangeCalculator {

    private DateTimeRangeCalculator() {

    }

    /**
     * 주어진 날짜와 기간 유형에 따라 시작 시각과 종료 시각을 계산하여 {@link DateTimeRange} 객체를 반환합니다.
     *
     * @param periodValue 기간 값 (예: 특정 날짜)
     * @param periodType  기간 유형 (일간, 주간, 월간, 연간)
     * @return 해당 기간의 시작 시각과 종료 시각을 포함하는 {@link DateTimeRange} 객체
     */
    public static DateTimeRange getStartDateAndEndDateByPeriod(LocalDate periodValue,
            RankingPeriodType periodType) {

        LocalDateTime startDateTime = getStartTimeOfDate(periodValue);
        LocalDateTime endDateTime = getEndTimeByPeriodAndDate(periodValue, periodType);
        return new DateTimeRange(startDateTime, endDateTime);
    }

    /**
     * 주어진 날짜에 따라 시작 시각을 계산합니다.
     *
     * @param periodValue 시작 날짜 (예: 특정 날짜)
     * @return 해당 기간의 시작 시각을 포함하는 {@link LocalDateTime} 객체
     */
    private static LocalDateTime getStartTimeOfDate(LocalDate periodValue) {

        return periodValue.atStartOfDay();
    }

    /**
     * 주어진 기간 유형과 날짜에 따라 종료 시각을 계산합니다.
     * todo weekly, monthly, yearly에 대해 각각 일/월, 1일, 1월이 시작이 아니라면 예외/자동 보정 처리 필요
     *
     * @param periodValue 기간 값 (예: 특정 날짜)
     * @param periodType  기간 유형 (일간, 주간, 월간, 연간)
     * @return 해당 기간의 종료 시각
     */
    private static LocalDateTime getEndTimeByPeriodAndDate(LocalDate periodValue,
            RankingPeriodType periodType) {

        return switch (periodType) {
            case DAILY -> periodValue.plusDays(1).atStartOfDay().minusNanos(1);
            case WEEKLY -> periodValue.plusWeeks(1).atStartOfDay().minusNanos(1);
            case MONTHLY -> periodValue.plusMonths(1).atStartOfDay().minusNanos(1);
            case YEARLY -> periodValue.plusYears(1).atStartOfDay().minusNanos(1);
        };
    }

}