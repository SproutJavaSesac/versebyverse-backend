package today.sesac.shoutify.ranking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;

/**
 * 순위(랭킹) 정보입니다.
 */
@Getter
@Entity
@Table(name = "rankings"
	// ,
	// uniqueConstraints = {
	// 	@UniqueConstraint(columnNames = {"member_id", "category"})
	// }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ranking extends BaseEntity {

	private static final String NEW_RANK_CHANGE = "NEW";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// /**
	//  * 해당 순위의 회원
	//  */
	// @ManyToOne(fetch =FetchType.LAZY)
	// @JoinColumn(name = "member_id")
	// private Member member;

	/**
	 * 순위(랭킹)가 속한 카테고리
	 */

	@NotNull
	@Column(columnDefinition = "tinyint unsigned")
	private int category;

	/**
	 * 해당 카테고리의 점수
	 */
	private int score;

	/**
	 * 해당 카테고리의 순위
	 */
	private int ranks;

	/**
	 * 이전 순위
	 * <p>
	 *     null이면 이전 순위가 없음을 의미합니다.
	 * </p>
	 */
	private Integer previousRank;

	/**
	 * 순위 변화
	 * <p>
	 *     -1이면 이전 순위보다 1등 하락,
	 *     +1이면 이전 순위보다 1등 상승,
	 *     "-"이면 이전 순위와 동일함,
	 *     "NEW"이면 이전 순위가 없음을 의미
	 * </p>
	 */
	@NotNull
	@Column(length = 30)
	private String rankChange;

	private Ranking(
		int category,
		int score,
		int ranks,
		Integer previousRank,
		String rankChange
	) {
		this.category = category;
		this.score = score;
		this.ranks = ranks;
		this.previousRank = previousRank;
		this.rankChange = rankChange;
	}

	/**
	 * 처음으로 순위에 진입합니다. 순위(랭킹)를 생성합니다.
	 * @param category 순위 카테고리
	 * @param score 초기 점수
	 * @param rank 초기 순위
	 * @return 랭킹 객체
	 */
	public static Ranking create(
		RankingCategory category,
		int score, int rank
	) {
		return new Ranking(
			category.getCode(), // 카테고리 값
			score, // 초기 점수
			rank, // 초기 순위
			null, // 초기 이전 순위 X
			NEW_RANK_CHANGE // 초기 랭크 변화
		);
	}
}
