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

        if (concept != null && !concept.isEmpty()) {
            filtered.removeIf(post -> !concept.equals(post.getConceptType()));

            int pageNum = (page != null) ? page : 1;
            int start = (pageNum - 1) * limit;
            int end = Math.min(start + limit, filtered.size());

            List<PostSummaryResponseDto> paged =
                    (start < filtered.size()) ? filtered.subList(start, end) : List.of();
            int totalCount = filtered.size();
            int totalPages = (int) Math.ceil((double) totalCount / limit);

            return ApiResponse.success(Map.of(
                    "posts", paged,
                    "currentPage", pageNum,
                    "totalPages", totalPages,
                    "totalCount", totalCount,
                    "hasNext", pageNum < totalPages,
                    "hasPrevious", pageNum > 1
            ));
        } else {
            if (cursor != null) {
                filtered.removeIf(post -> post.getPostId() >= cursor);
            }

            filtered.sort(Comparator.comparing(PostSummaryResponseDto::getPostId).reversed());
            List<PostSummaryResponseDto> sliced = filtered.stream().limit(limit).toList();
            Integer nextCursor =
                    sliced.isEmpty() ? null : sliced.get(sliced.size() - 1).getPostId().intValue();

            return ApiResponse.success(Map.of(
                    "posts", sliced,
                    "currentPage", 1,
                    "totalPages", 1,
                    "totalCount", filtered.size(),
                    "hasNext", sliced.size() == limit,
                    "hasPrevious", false,
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
