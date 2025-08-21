package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;

/**
 * 다른 회원의 순위(랭킹) 정보를 나타내는 DTO.
 */
public record MemberRankingListResponseDto(
        RankingCategory category,
        int maxCount,
        RankingPeriodType periodType,
        List<MemberRankingSummary> rankings
) {

}