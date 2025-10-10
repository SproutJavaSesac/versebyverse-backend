package today.sesac.versebyverse.ranking.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;

/**
 * 특정 카테고리와 기간 유형에 해당하는 순위(랭킹) 정보를 담은 목록을 보여주는 DTO.
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RankingListResponseDto {

    private RankingCategory category;

    private RankingPeriodType periodType;

    private LocalDate periodValue;

    private List<RankingSingleResponseDto> rankings;

}