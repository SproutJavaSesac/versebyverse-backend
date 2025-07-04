package today.sesac.shoutify.post.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;

/**
 * 게시물 조회 관련 컨트롤러.
 */
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostQueryController {

    /**
     * 게시물 단건 조회.
     */
    @GetMapping("/{postId}")
    public ApiResponse<Object> getPost(@PathVariable Long postId) {
        Object response = Map.of(
                "postId", postId,
                "nickname", "작성자닉네임",
                "afterTitle", "변환된 제목",
                "afterContent", "변환된 내용",
                "createdAt", LocalDateTime.now(),
                "conceptType", "컨셉타입",
                "reactionCount", 10,
                "commentCount", 5,
                "imageUrl", "https://example.com/image.jpg"
        );
        return ApiResponse.success(response);
    }

    /**
     * 게시물 목록 조회.
     */
    @GetMapping
    public ApiResponse<Object> getPosts(
            @RequestParam String sort,
            @RequestParam(required = false) String concept,
            @RequestParam(required = false) Integer cursor,
            @RequestParam(required = false) Integer page,
            @RequestParam Integer limit,
            @RequestParam(required = false) String keyword) {

        // sort에 따라 다른 정렬 데이터
        List<Object> posts;

        if ("reactions".equals(sort)) {
            // 반응 많은 순
            posts = List.of(
                    Map.of("postId", 10L, "nickname", "인기왕", "afterTitle", "대박 반응글", "afterContent",
                            "모두가 좋아하는 글",
                            "createdAt", LocalDateTime.now().minusHours(3), "reactionCount", 100,
                            "commentCount", 15),
                    Map.of("postId", 11L, "nickname", "핫한사람", "afterTitle", "인기글2", "afterContent",
                            "반응 폭발",
                            "createdAt", LocalDateTime.now().minusHours(5), "reactionCount", 80,
                            "commentCount", 12),
                    Map.of("postId", 12L, "nickname", "좋아요킹", "afterTitle", "반응좋은글", "afterContent",
                            "많은 반응",
                            "createdAt", LocalDateTime.now().minusHours(2), "reactionCount", 60,
                            "commentCount", 8)
            );
        } else if ("comments".equals(sort)) {
            // 댓글 많은 순
            posts = List.of(
                    Map.of("postId", 20L, "nickname", "토론왕", "afterTitle", "댓글폭발글", "afterContent",
                            "논란의 중심",
                            "createdAt", LocalDateTime.now().minusHours(4), "reactionCount", 30,
                            "commentCount", 50),
                    Map.of("postId", 21L, "nickname", "대화킹", "afterTitle", "댓글많은글", "afterContent",
                            "활발한 토론",
                            "createdAt", LocalDateTime.now().minusHours(6), "reactionCount", 25,
                            "commentCount", 45),
                    Map.of("postId", 22L, "nickname", "소통러", "afterTitle", "댓글천국", "afterContent",
                            "소통 좋아요",
                            "createdAt", LocalDateTime.now().minusHours(1), "reactionCount", 20,
                            "commentCount", 40)
            );
        } else { // latest (최신순)
            posts = List.of(
                    Map.of("postId", 1L, "nickname", "방금작성자", "afterTitle", "방금 올린 글",
                            "afterContent", "따끈따끈 새글",
                            "createdAt", LocalDateTime.now(), "reactionCount", 5, "commentCount",
                            2),
                    Map.of("postId", 2L, "nickname", "최근작성자", "afterTitle", "1시간전 글",
                            "afterContent", "비교적 최신",
                            "createdAt", LocalDateTime.now().minusHours(1), "reactionCount", 8,
                            "commentCount", 4),
                    Map.of("postId", 3L, "nickname", "어제작성자", "afterTitle", "어제 올린 글",
                            "afterContent", "하루 전 글",
                            "createdAt", LocalDateTime.now().minusDays(1), "reactionCount", 12,
                            "commentCount", 6)
            );
        }

        // limit만큼만 잘라서 반환 (페이지네이션/무한스크롤용)
        if (limit != null && limit < posts.size()) {
            posts = posts.subList(0, limit);
        }

        Object response = Map.of("posts", posts);
        return ApiResponse.success(response);
    }
}