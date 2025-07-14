package today.sesac.shoutify.ranking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntityOnlyCreatedAt;
import today.sesac.shoutify.member.entity.Member;

/**
 * 순위(랭킹) 정보입니다. 정적 팩토리 메서드
 * {@link #createFirstEntry(Member, RankingCategory, int, int, RankingPeriodType)}를 통해 생성합니다.
 */
@Getter
@Entity
@Table(name = "rankings",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_id", "category", "period_type"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ranking extends BaseEntityOnlyCreatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    /**
     * 해당 순위의 회원.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * 순위(랭킹)가 속한 카테고리.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private RankingCategory category;

    /**
     * 해당 카테고리의 점수.
     */
    @Column(columnDefinition = "int unsigned", nullable = false)
    private int score;

    /**
     * 해당 카테고리의 순위.
     */
    @Column(columnDefinition = "int unsigned", nullable = false)
    private int ranks;

    /**
     * 이전 순위.
     *
     * <p>null이면 이전 순위가 없음을 의미합니다.</p>
     */
    @Column(columnDefinition = "int unsigned")
    private Integer previousRank;

    /**
     * 순위(랭킹)의 기간 타입.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private RankingPeriodType periodType;

    private Ranking(
            Member member,
            RankingCategory category,
            int score,
            int ranks,
            Integer previousRank,
            RankingPeriodType periodType
    ) {

        this.member = member;
        this.category = category;
        this.score = score;
        this.ranks = ranks;
        this.previousRank = previousRank;
        this.periodType = periodType;
    }

    /**
     * 처음으로 순위에 진입합니다. 순위(랭킹)를 생성합니다.
     *
     * @param member     순위에 진입하는 회원
     * @param category   순위 카테고리
     * @param score      초기 점수
     * @param rank       초기 순위
     * @param periodType 순위의 기간 타입
     * @return 랭킹 객체
     */
    public static Ranking createFirstEntry(
            Member member,
            RankingCategory category,
            int score, int rank,
            RankingPeriodType periodType
    ) {

        return new Ranking(
                member,
                category,
                score,
                rank,
                null, // 초기 이전 순위 X
                periodType
        );
    }

    /**
     * 순위(랭킹)를 생성합니다. 이전 순위가 있는 경우에 사용합니다.
     *
     * @param member          순위에 진입하는 회원
     * @param rankingCategory 순위 카테고리
     * @param postCount       순위에 반영되는 게시글 수
     * @param rank            현재 순위
     * @param previousRank    이전 순위
     * @param periodType      순위의 기간 타입
     * @return 랭킹 객체
     */
    public static Ranking createWithPreviousRank(Member member, RankingCategory rankingCategory,
            int postCount,
            int rank, int previousRank, RankingPeriodType periodType) {

        return new Ranking(
                member,
                rankingCategory,
                postCount,
                rank,
                previousRank,
                periodType
        );
    }
}
