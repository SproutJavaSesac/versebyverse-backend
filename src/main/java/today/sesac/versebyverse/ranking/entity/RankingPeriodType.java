package today.sesac.versebyverse.ranking.entity;

import lombok.Getter;

/**
 * 순위(랭킹) 기간 타입.
 */
@Getter
public enum RankingPeriodType {
    DAILY("일간"),
    WEEKLY("주간"),
    MONTHLY("월간"),
    YEARLY("연간");

    /**
     * 화면에 표시되는 이름입니다.
     */
    private final String displayName;

    RankingPeriodType(String displayName) {

        this.displayName = displayName;
    }
}