package today.sesac.versebyverse.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.post.dto.request.PostCreateRequestDto;
import today.sesac.versebyverse.post.dto.response.PostCreateResponseDto;
import today.sesac.versebyverse.post.service.PostCommandService;

/**
 * 게시물 생성, 삭제 , 숨김 기능 controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostCommandController {

    private final PostCommandService postCommandService;

    /**
     * 게시글 작성.
     *
     * @param postCreateRequestDto 게시글 작성 dto
     * @param userPrincipal        회원 id
     * @return 게시글 생성 dto
     */
    @PostMapping
    public ApiResponse<PostCreateResponseDto> savePost(
            @Valid @ModelAttribute PostCreateRequestDto postCreateRequestDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        PostCreateResponseDto postCreateResponseDto =
                postCommandService.savePost(postCreateRequestDto, memberId);
        return ApiResponse.success(postCreateResponseDto);
    }

    /**
     * 게시글 삭제.
     *
     * @param postId        게시글 id
     * @param userPrincipal 회원 id
     * @return 문자열
     */
    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        postCommandService.deletePost(postId, memberId);
        return ApiResponse.success("게시물 삭제가 성공했습니다");
    }

    /**
     * 게시글 숨김.
     *
     * @param postId        게시글 id
     * @param userPrincipal 회원 id
     * @return 문자열
     */
    @PatchMapping("/{postId}/hide")
    public ApiResponse<String> hidePost(@PathVariable Long postId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        postCommandService.hidePost(postId, memberId);
        return ApiResponse.success("게시물 숨기기가 성공했습니다");
    }

    /**
     * 게시글 숨김 해제.
     *
     * @param postId        게시글 id
     * @param userPrincipal 회원 id
     * @return 문자열
     */
    @PatchMapping("{postId}/unhide")
    public ApiResponse<String> unhidePost(@PathVariable Long postId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        postCommandService.unhidePost(postId, memberId);
        return ApiResponse.success("게시물 숨기기가 취소됐습니다");
    }
}