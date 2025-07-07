package today.sesac.shoutify.post.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.global.response.PaginationDto;
import today.sesac.shoutify.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.shoutify.post.dto.response.PostSummaryResponseDto;
import today.sesac.shoutify.post.service.PostQueryService;

/**
 * 게시글 조회 관련 controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostQueryController {
    private final PostQueryService postQueryService;

    @GetMapping
    public ApiResponse<Map<String, Object>> getAllPosts(
            @RequestParam(name = "sort") String sort,
            @RequestParam(name = "concept", required = false) String concept,
            @RequestParam(name = "cursor", required = false) Integer cursor,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        List<PostSummaryResponseDto> allPosts = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            allPosts.add(new PostSummaryResponseDto(
                    (long) i,
                    "사용자" + i,
                    i + "번째 글",
                    "내용 " + i,
                    LocalDateTime.now().minusDays(i),
                    i * 2,
                    i,
                    (i % 2 == 0) ? "poetry" : "novel",
                    "https://example.com/image" + i + ".jpg"
            ));
        }

        List<PostSummaryResponseDto> filtered = new ArrayList<>(allPosts);

        //컨셉별 필터링 + 페이지네이션
        if (concept != null && !concept.isEmpty()) {
            filtered.removeIf(post -> !concept.equals(post.getConceptType()));

            switch (sort) {
                case "latest" -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getCreatedAt).reversed());
                case "comments" -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getCommentCount).reversed());
                case "reactions" -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getReactionCount).reversed());
                default -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getCreatedAt).reversed());
            }

            int pageNum = (page != null) ? page : 0;
            int start = pageNum * limit;
            int end = Math.min(start + limit, filtered.size());

            List<PostSummaryResponseDto> paged =
                    (start < filtered.size()) ? filtered.subList(start, end) : List.of();
            int totalCount = filtered.size();
            int totalPages = (int) Math.ceil((double) totalCount / limit);

            // PaginationDto 사용
            PaginationDto pagination = new PaginationDto(
                    pageNum,                    // 0부터
                    totalPages,                 // 1부터
                    totalCount,
                    limit,
                    pageNum < totalPages - 1,
                    pageNum > 0
            );
            return ApiResponse.success(Map.of(
                    "posts", paged,
                    "pagination", pagination
            ));
        } else { //커서 기반 페이지네이션
            if (cursor != null) {
                filtered.removeIf(post -> post.getPostId() >= cursor);
            }

            switch (sort) {
                case "latest" -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getCreatedAt).reversed());
                case "comments" -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getCommentCount).reversed());
                case "reactions" -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getReactionCount).reversed());
                default -> filtered.sort(
                        Comparator.comparing(PostSummaryResponseDto::getCreatedAt).reversed());
            }

            List<PostSummaryResponseDto> sliced = filtered.stream().limit(limit).toList();
            Integer nextCursor =
                    sliced.isEmpty() ? null : sliced.get(sliced.size() - 1).getPostId().intValue();

            PaginationDto pagination = new PaginationDto(
                    0,                          // currentPage (커서 방식에서는 의미 없음)
                    1,                          // totalPages (커서 방식에서는 의미 없음)
                    filtered.size(),            // totalCount
                    limit,                      // pageSize
                    sliced.size() == limit,     // hasNext
                    false                       // hasPrevious (커서 방식에서는 false)
            );

            return ApiResponse.success(Map.of(
                    "posts", sliced,
                    "pagination", pagination,
                    "nextCursor", nextCursor
            ));
        }
    }

//TODO 현재 사용자 memberId 1로 하드코딩 추후 변경 예정

    /**
     * 게시글 단건 상세 조회.
     */
    @GetMapping("/{postId}")
    public ApiResponse<PostSingleQueryResponseDto> getSinglePostDetail(
            @PathVariable("postId") Long postId) {
        Long memberId = 1L;
        PostSingleQueryResponseDto postSingleQueryResponseDto =
                postQueryService.getPostDetail(postId, memberId);
        return ApiResponse.success(postSingleQueryResponseDto);
    }
}
