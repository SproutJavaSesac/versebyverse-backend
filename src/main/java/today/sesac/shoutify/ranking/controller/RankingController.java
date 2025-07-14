package today.sesac.shoutify.ranking.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.ranking.dto.response.RankingListResponseDto;
import today.sesac.shoutify.ranking.entity.RankingCategory;
import today.sesac.shoutify.ranking.entity.RankingPeriodType;
import today.sesac.shoutify.ranking.service.RankingService;

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
     * @param page        페이지 번호 (기본값: 0)
     * @param size        페이지 크기 (기본값: 20)
     * @param direction   정렬 방향 (기본값: DESC)
     * @param sortBy      정렬 기준 필드 (기본값: createdAt)
     * @return 해당 카테고리와 기간 유형에 해당하는 순위 목록
     */
    @GetMapping
    public ApiResponse<RankingListResponseDto> getRankingsByCategoryAndPeriod(
            @RequestParam("category") RankingCategory category,
            @RequestParam(value = "periodType") RankingPeriodType periodType,
            @RequestParam(value = "periodValue") LocalDate periodValue,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "direction", defaultValue = "DESC") Direction direction,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sortBy
    ) {

        return ApiResponse.success(
                rankingService.getRankingsByCategoryAndPeriod(category, periodType, periodValue,
                        PageRequest.of(page, size, Sort.by(direction, sortBy))));
    }
}