package today.sesac.shoutify.ranking.entity;

import lombok.Getter;

@Getter
public enum RankingCategory {
	SWEAR_SCORE(0, "욕설 점수"),
	REACTION(1, "반응 수"),
	POST(2, "게시글 수"),
	;

	private final int code;
	private final String description;

	RankingCategory(int code, String description) {
		this.code = code;
		this.description = description;
	}
}
