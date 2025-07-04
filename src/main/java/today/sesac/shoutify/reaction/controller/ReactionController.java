package today.sesac.shoutify.reaction.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;

/**
 * 게시물 및 댓글 반응 관련 컨트롤러.
 */
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ReactionController {

    /**
     * 게시물에 반응하기.
     */
    @PostMapping("/{postId}/reactions")
    public ApiResponse<Object> reactToPost(
            @PathVariable Long postId,
            @RequestBody Map<String, String> request) {

        Object response = Map.of(
                "status", "added",
                "reaction", request.get("emotion"),
                "reactionCount", 11
        );
        return ApiResponse.success(response);
    }

    /**
     * 댓글에 반응하기.
     */
    @PostMapping("/{postId}/comments/{commentId}/reactions")
    public ApiResponse<Object> reactToComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody Map<String, String> request) {

        Object response = Map.of(
                "status", "added",
                "reaction", request.get("emotion"),
                "reactionCount", 6
        );
        return ApiResponse.success(response);
    }
}
