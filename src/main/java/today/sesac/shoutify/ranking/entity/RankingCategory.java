package today.sesac.shoutify.ranking.entity;

import lombok.Getter;

/**
 * 순위(랭킹) 카테고리.
 */
@Getter
public enum RankingCategory {
    POST("게시글 수");

    /**
     * 화면에 표시되는 이름.
     */
    private final String displayName;

    RankingCategory(String displayName) {
        this.displayName = displayName;
    }
}
