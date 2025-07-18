package today.sesac.versebyverse.member.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.ranking.entity.Ranking;

/**
 * 내 순위(랭킹) 요약 정보를 나타내는 DTO.
 */
public record MyRankingSummary(
        int rank,
        int categoryValue,
        Integer previousRank,
        String rankChange,
        LocalDateTime createdAt
) {

    /**
     * Ranking 객체를 사용하여 MyRankingSummary 객체를 생성합니다.
     *
     * @param ranking    순위(랭킹) 정보가 담긴 Ranking 객체
     * @param rankChange 순위 변화에 대한 설명
     */
    public MyRankingSummary(Ranking ranking, String rankChange) {

        this(
                ranking.getRank(),
                ranking.getScore(),
                ranking.getPreviousRank(),
                rankChange,
                ranking.getCreatedAt()
        );
    }
}