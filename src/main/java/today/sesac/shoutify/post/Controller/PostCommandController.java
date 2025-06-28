package today.sesac.shoutify.post.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.post.dto.request.PostCreateRequestDto;
import today.sesac.shoutify.post.dto.response.PostCreateResponseDto;
import today.sesac.shoutify.post.service.PostCommandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostCommandController {

    private final PostCommandService postCommandService;
    Long memberId = 1L; //TODO 현재 사용자 memberId 1로 하드코딩 추후 변경 예정

    /**
     * 게시물 작성
     */
    @PostMapping
    public ApiResponse<PostCreateResponseDto> savePost(
            @RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostCreateResponseDto postCreateResponseDto =
                postCommandService.savePost(postCreateRequestDto, memberId);
        return ApiResponse.success(postCreateResponseDto);
    }

    /**
     * 게시물삭제
     */
    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId) {
        postCommandService.deletePost(postId, memberId);
        return ApiResponse.success("게시물 삭제가 성공했습니다");
    }

    /**
     * 게시물 숨기기
     */
    @PatchMapping("/{postId}/hide")
    public ApiResponse<String> hidePost(@PathVariable Long postId) {
        postCommandService.hidePost(postId, memberId);
        return ApiResponse.success("게시물 숨기기가 성공했습니다");
    }

    /**
     * 게시물 숨김 해제
     */
    @PatchMapping("{postId}/unhide")
    public ApiResponse<String> unhidePost(@PathVariable Long postId) {
        postCommandService.unhidePost(postId, memberId);
        return ApiResponse.success("게시물 숨기기가 취소됐습니다");
    }
}
