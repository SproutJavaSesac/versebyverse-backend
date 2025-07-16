package today.sesac.versebyverse.ranking.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;
import today.sesac.versebyverse.ranking.response.RankingListResponseDto;
import today.sesac.versebyverse.ranking.service.RankingService;

/**
 * 순위(랭킹) 정보를 관리하는 컨트롤러.
 */
@RestController
@RequestMapping("api/v1/rankings")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    /**
     * 특정 카테고리와 기간 유형에 해당하는 순위(랭킹) 정보를 조회합니다.
     *
     * @param category    조회할 순위(랭킹) 카테고리
     * @param periodType  조회할 순위(랭킹) 기간 유형
     * @param periodValue 조회할 순위(랭킹) 기간 값 (예: 특정 날짜)
     * @param pageable    페이징 정보
     * @return 해당 카테고리와 기간 유형에 해당하는 순위 목록
     */
    @GetMapping
    public ApiResponse<RankingListResponseDto> getRankingsByCategoryAndPeriod(
            @RequestParam RankingCategory category,
            @RequestParam RankingPeriodType periodType,
            @RequestParam LocalDate periodValue,
            @PageableDefault(page = 0, size = 20,
                    sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        return ApiResponse.success(
                rankingService.getRankingsByCategoryAndPeriod(
                        category, periodType, periodValue, pageable));
    }
}