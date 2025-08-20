package today.sesac.versebyverse.post.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.post.dto.request.PostCreateRequestDto;
import today.sesac.versebyverse.post.dto.response.PostCreateResponseDto;
import today.sesac.versebyverse.post.service.PostCommandService;

/**
 * 게시물 생성, 삭제 , 숨김 기능 controller.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostCommandController {

    private final PostCommandService postCommandService;
    //TODO 현재 사용자 memberId 1로 하드코딩 추후 변경 예정

    /**
     * 게시물 작성.
     */
    @PostMapping
    public ApiResponse<PostCreateResponseDto> savePost(
            @Valid @ModelAttribute PostCreateRequestDto postCreateRequestDto) throws IOException {

        Long memberId = 1L;
        log.info(postCreateRequestDto.toString());
        PostCreateResponseDto postCreateResponseDto =
                postCommandService.savePost(postCreateRequestDto, memberId);
        return ApiResponse.success(postCreateResponseDto);
    }

    /**
     * 게시물 삭제.
     */
    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId) {

        Long memberId = 1L;
        postCommandService.deletePost(postId, memberId);
        return ApiResponse.success("게시물 삭제가 성공했습니다");
    }

    /**
     * 게시물 숨김.
     */
    @PatchMapping("/{postId}/hide")
    public ApiResponse<String> hidePost(@PathVariable Long postId) {

        Long memberId = 1L;
        postCommandService.hidePost(postId, memberId);
        return ApiResponse.success("게시물 숨기기가 성공했습니다");
    }

    /**
     * 게시물 숨김 해제.
     */
    @PatchMapping("{postId}/unhide")
    public ApiResponse<String> unhidePost(@PathVariable Long postId) {

        Long memberId = 1L;
        postCommandService.unhidePost(postId, memberId);
        return ApiResponse.success("게시물 숨기기가 취소됐습니다");
    }
}