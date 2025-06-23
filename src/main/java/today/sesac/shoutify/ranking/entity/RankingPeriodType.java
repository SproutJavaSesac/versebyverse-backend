package today.sesac.shoutify.ranking.entity;

import lombok.Getter;

/**
 * 순위(랭킹) 기간 타입.
 */
@Getter
public enum RankingPeriodType {
    DAILY("일간");

    /**
     * 화면에 표시되는 이름입니다.
     */
    private final String displayName;

    RankingPeriodType(String displayName) {
        this.displayName = displayName;
    }
}
