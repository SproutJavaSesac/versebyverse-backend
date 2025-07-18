package today.sesac.versebyverse.ranking.dto.response;

import java.time.LocalDate;
import java.util.List;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;

/**
 * 특정 카테고리와 기간 유형에 해당하는 순위(랭킹) 정보를 담은 목록을 보여주는 DTO.
 *
 * @param category    조회할 순위(랭킹) 카테고리
 * @param periodType  조회할 순위(랭킹) 기간 유형
 * @param periodValue 조회할 순위(랭킹) 기간 값 (예: 특정 날짜)
 * @param rankings    해당 카테고리와 기간 유형에 해당하는 순위 목록
 */
public record RankingListResponseDto(

        RankingCategory category,
        RankingPeriodType periodType,
        LocalDate periodValue,
        List<RankingSingleResponseDto> rankings) {

}