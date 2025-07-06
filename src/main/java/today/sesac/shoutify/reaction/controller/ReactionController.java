package today.sesac.shoutify.reaction.controller;

import java.util.HashMap;
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

        String emotion = request.get("emotion");
        String action = request.getOrDefault("action", "add"); // 기본값 "add"

        // 목업용 간단한 응답 생성
        String status = switch (action) {
            case "add" -> "added";
            case "remove" -> "removed";
            default -> "added";
        };

        // 목업용 카운트 (실제로는 DB에서 가져와야 함)
        int reactionCount = switch (action) {
            case "add" -> 12;      // +1
            case "remove" -> 10;   // -1
            default -> 11;
        };

        Object response = Map.of(
                "status", status,
                "reaction", emotion,
                "reactionCount", reactionCount,
                "reactionDetails", getReactionDetails(emotion, action)
        );
        return ApiResponse.success(response);
    }

    private Map<String, Integer> getReactionDetails(String emotion, String action) {
        //반응별 고정 갯수
        Map<String, Integer> details = new HashMap<>();
        details.put("happy", 15);
        details.put("sad", 5);
        details.put("angry", 2);
        details.put("excited", 0);
        details.put("confused", 1);
        details.put("proud", 1);

        // 해당 이모티콘 카운트 조정, change의 경우
        if (action.equals("add")) {
            details.put(emotion, details.get(emotion) + 1);
        } else if (action.equals("remove")) {
            details.put(emotion, Math.max(0, details.get(emotion) - 1));
        }

        return details;
    }

    /**
     * 댓글에 반응하기.
     */
    @PostMapping("/{postId}/comments/{commentId}/reactions")
    public ApiResponse<Object> reactToComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody Map<String, String> request) {

        String emotion = request.get("emotion");
        String action = request.getOrDefault("action", "add"); // 기본값 "add"

        // 목업용 간단한 응답 생성
        String status = switch (action) {
            case "add" -> "added";
            case "remove" -> "removed";
            default -> "added";
        };

        // 목업용 카운트 (실제로는 DB에서 가져와야 함)
        int reactionCount = switch (action) {
            case "add" -> 8;       // +1
            case "remove" -> 6;    // -1
            case "change" -> 7;    // 동일
            default -> 7;
        };

        Object response = Map.of(
                "status", status,
                "reaction", emotion,
                "reactionCount", reactionCount,
                "reactionDetails", getCommentReactionDetails(emotion, action)
        );
        return ApiResponse.success(response);
    }

    private Map<String, Integer> getCommentReactionDetails(String emotion, String action) {
        Map<String, Integer> details = new HashMap<>();
        // 댓글 반응은 게시물보다 적게 설정
        details.put("happy", 8);
        details.put("sad", 2);
        details.put("angry", 1);
        details.put("excited", 0);
        details.put("confused", 0);
        details.put("proud", 1);

        // 해당 이모티콘 카운트 조정
        if (action.equals("add")) {
            details.put(emotion, details.get(emotion) + 1);
        } else if (action.equals("remove")) {
            details.put(emotion, Math.max(0, details.get(emotion) - 1));
        }

        return details;
    }
}
