package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;

/**
 * 내 순위(랭킹) 정보를 나타내는 DTO.
 */
public record MyRankingListResponseDto(
        RankingCategory category,
        int maxCount,
        RankingPeriodType periodType,
        List<MyRankingSummary> rankings
) {

}