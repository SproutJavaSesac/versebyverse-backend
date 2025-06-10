package today.sesac.shoutify.ranking.domain;

import lombok.Getter;

@Getter
public enum RankingCategory {
	SWEAR_SCORE(0, "욕설 점수"),
	WEEKLY(1, "주간"),
	MONTHLY(2, "월간");

	private final int code;
	private final String description;

	RankingCategory(int code, String description) {
		this.code = code;
		this.description = description;
	}
}
