package today.sesac.shoutify.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.post.dto.request.PostCreateRequest;
import today.sesac.shoutify.post.dto.response.PostCreateResponse;
import today.sesac.shoutify.post.service.PostService;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 게시물 작성
     */
    @PostMapping("/posts")
    public ResponseEntity<ApiResponse<PostCreateResponse>> savePost(
            @RequestBody PostCreateRequest request) {
        PostCreateResponse response = postService.savePost(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    /**
     * 게시물삭제
     */

    /**
     * 게시물 수정
     */
}
