package today.sesac.shoutify.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.post.dto.request.PostCreateRequest;
import today.sesac.shoutify.post.dto.response.PostCreateResponse;
import today.sesac.shoutify.post.repository.PostRepository;
import today.sesac.shoutify.post.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    /**
     * 게시물 작성
     */
    @PostMapping()
    public ResponseEntity<ApiResponse<PostCreateResponse>> savePost(
            @RequestBody PostCreateRequest request) {
        PostCreateResponse response = postService.savePost(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 게시물삭제
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(ApiResponse.success("삭제가 성공했습니다"));
    }
    /**
     * 게시물 수정
     */
}
