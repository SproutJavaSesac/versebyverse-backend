package today.sesac.versebyverse.member.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.ranking.entity.Ranking;

/**
 * 다른 회원의 순위(랭킹) 요약 정보를 나타내는 DTO.
 */
public record MemberRankingSummary(
        int rank,
        int categoryValue,
        Integer previousRank,
        String rankChange,
        LocalDateTime createdAt
) {

    /**
     * Ranking 객체를 사용하여 MemberRankingSummary 객체를 생성합니다.
     *
     * @param ranking    순위(랭킹) 정보가 담긴 Ranking 객체
     * @param rankChange 순위 변화에 대한 설명
     */
    public MemberRankingSummary(Ranking ranking, String rankChange) {

        this(
                ranking.getRank(),
                ranking.getScore(),
                ranking.getPreviousRank(),
                rankChange,
                ranking.getCreatedAt()
        );
    }
}