package today.sesac.shoutify.post.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/posts")
public class PostQueryController {

    @GetMapping
    public ApiResponse<Map<String, Object>> getAllPosts(
            @RequestParam(name = "sort") String sort,
            @RequestParam(name = "concept", required = false) String concept,
            @RequestParam(name = "cursor", required = false) Integer cursor,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        List<Map<String, Object>> allPosts = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            allPosts.add(Map.of(
                    "postId", i,
                    "nickname", "사용자" + i,
                    "afterTitle", i + "번째 글",
                    "afterContent", "내용 " + i,
                    "createdAt", LocalDateTime.now().minusDays(i),
                    "reactionCount", i * 2,
                    "commentCount", i,
                    "conceptType", (i % 2 == 0) ? "poetry" : "novel",
                    "imageUrl", "https://example.com/image" + i + ".jpg"
            ));
        }

        List<Map<String, Object>> filtered = new ArrayList<>(allPosts);

        if (concept != null && !concept.isEmpty()) {
            filtered.removeIf(post -> !concept.equals(post.get("conceptType")));

            int pageNum = (page != null) ? page : 1;
            int start = (pageNum - 1) * limit;
            int end = Math.min(start + limit, filtered.size());

            List<Map<String, Object>> paged =
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
                filtered.removeIf(post -> (Integer) post.get("postId") >= cursor);
            }

            filtered = filtered.stream().sorted((a, b) -> ((Integer) b.get("postId")).compareTo(
                    (Integer) a.get("postId"))).toList();
            List<Map<String, Object>> sliced = filtered.stream().limit(limit).toList();
            Integer nextCursor =
                    sliced.isEmpty() ? null : (Integer) sliced.get(sliced.size() - 1).get("postId");

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

    @GetMapping("/{postId}")
    public ApiResponse<Map<String, Object>> getPostDetail(@PathVariable Long postId) {
        Map<String, Object> post = Map.of(
                "postId", postId,
                "nickname", "행복한고양이",
                "afterTitle", "오늘 날씨가 너무 좋네요!",
                "afterContent", "창문을 열고 있으니 바람이 시원하게 불어와서 기분이 정말 좋습니다. 이런 날에는 산책을 나가고 싶어지네요...",
                "createdAt", "2025-06-18T14:30:25.000Z",
                "conceptType", "daily",
                "reactionCount", 23,
                "reactionDetails",
                Map.of("happy", 15, "sad", 5, "angry", 2, "excited", 0, "confused", 1, "proud", 1),
                "commentCount", 8,
                "imageUrl", "https://my-bucket.s3.amazonaws.com/images/post-1-20250618.jpg"
        );
        return ApiResponse.success(post);
    }
}