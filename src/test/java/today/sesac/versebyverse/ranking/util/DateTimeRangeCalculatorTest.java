package today.sesac.versebyverse.ranking.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;

class DateTimeRangeCalculatorTest {

    @Test
    @DisplayName("[unit][정상] 일간 기간으로 시작/종료 시각을 계산한다")
    void calculateDailyPeriod() {
        // given
        LocalDate testDate = LocalDate.of(2024, 1, 15);
        RankingPeriodType periodType = RankingPeriodType.DAILY;

        // when
        DateTimeRange result = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(testDate,
                periodType);

        // then
        assertThat(result.startDateTime()).isEqualTo(LocalDateTime.of(2024, 1, 15, 0, 0, 0));
        assertThat(result.endDateTime()).isEqualTo(
                LocalDateTime.of(2024, 1, 15, 23, 59, 59, 999_999_999));
    }

    @Test
    @DisplayName("[unit][정상] 주간 기간으로 시작/종료 시각을 계산한다")
    void calculateWeeklyPeriod() {
        // given
        LocalDate testDate = LocalDate.of(2024, 1, 14);
        RankingPeriodType periodType = RankingPeriodType.WEEKLY;

        // when
        DateTimeRange result = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(testDate,
                periodType);

        // then
        assertThat(result.startDateTime()).isEqualTo(LocalDateTime.of(2024, 1, 14, 0, 0, 0));
        assertThat(result.endDateTime()).isEqualTo(
                LocalDateTime.of(2024, 1, 20, 23, 59, 59, 999_999_999));
    }

    @Test
    @DisplayName("[unit][정상] 월간 기간으로 시작/종료 시각을 계산한다")
    void calculateMonthlyPeriod() {
        // given
        LocalDate testDate = LocalDate.of(2024, 1, 2);
        RankingPeriodType periodType = RankingPeriodType.MONTHLY;

        // when
        DateTimeRange result = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(testDate,
                periodType);

        // then
        assertThat(result.startDateTime()).isEqualTo(LocalDateTime.of(2024, 1, 2, 0, 0, 0));
        assertThat(result.endDateTime()).isEqualTo(
                LocalDateTime.of(2024, 2, 1, 23, 59, 59, 999_999_999));
    }

    @Test
    @DisplayName("[unit][정상] 연간 기간으로 시작/종료 시각을 계산한다")
    void calculateYearlyPeriod() {
        // given
        LocalDate testDate = LocalDate.of(2024, 1, 2);
        RankingPeriodType periodType = RankingPeriodType.YEARLY;

        // when
        DateTimeRange result = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(testDate,
                periodType);

        // then
        assertThat(result.startDateTime()).isEqualTo(LocalDateTime.of(2024, 1, 2, 0, 0, 0));
        assertThat(result.endDateTime()).isEqualTo(
                LocalDateTime.of(2025, 1, 1, 23, 59, 59, 999_999_999));
    }

    @Test
    @DisplayName("[unit][경계] 윤년 2월의 월간 기간을 계산한다")
    void calculateLeapYearFebruary() {
        // given
        LocalDate leapYearFeb = LocalDate.of(2024, 2, 1);
        RankingPeriodType periodType = RankingPeriodType.MONTHLY;

        // when
        DateTimeRange result = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(leapYearFeb,
                periodType);

        // then
        assertThat(result.endDateTime()).isEqualTo(
                LocalDateTime.of(2024, 2, 29, 23, 59, 59, 999_999_999));
    }

    @Test
    @DisplayName("[unit][경계] 연말 일간 기간을 계산한다")
    void calculateYearEndDaily() {
        // given
        LocalDate yearEnd = LocalDate.of(2024, 12, 31);
        RankingPeriodType periodType = RankingPeriodType.DAILY;

        // when
        DateTimeRange result = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(yearEnd,
                periodType);

        // then
        assertThat(result.startDateTime()).isEqualTo(LocalDateTime.of(2024, 12, 31, 0, 0, 0));
        assertThat(result.endDateTime()).isEqualTo(
                LocalDateTime.of(2024, 12, 31, 23, 59, 59, 999_999_999));
    }

    @ParameterizedTest
    @CsvSource({
            "DAILY, 2024-01-15, 2024-01-15T00:00:00, 2024-01-15T23:59:59.999999999",
            "WEEKLY, 2024-01-15, 2024-01-15T00:00:00, 2024-01-21T23:59:59.999999999",
            "MONTHLY, 2024-01-01, 2024-01-01T00:00:00, 2024-01-31T23:59:59.999999999",
            "YEARLY, 2024-01-01, 2024-01-01T00:00:00, 2024-12-31T23:59:59.999999999"
    })
    @DisplayName("각 기간 타입별로 올바른 시작/종료 시각을 계산한다")
    void calculatePeriodRange(RankingPeriodType periodType, LocalDate inputDate,
                              LocalDateTime expectedStart, LocalDateTime expectedEnd) {
        // when
        DateTimeRange result = DateTimeRangeCalculator.getStartDateAndEndDateByPeriod(inputDate,
                periodType);

        // then
        assertThat(result.startDateTime()).isEqualTo(expectedStart);
        assertThat(result.endDateTime()).isEqualTo(expectedEnd);
    }
}