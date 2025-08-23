package today.sesac.versebyverse.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.post.dto.request.PostProofreadCreateRequestDto;
import today.sesac.versebyverse.post.dto.request.PostProofreadPublishRequestDto;
import today.sesac.versebyverse.post.dto.response.PostCreateResponseDto;
import today.sesac.versebyverse.post.dto.response.PostProofreadCreateResponseDto;
import today.sesac.versebyverse.post.service.PostProofreadService;

/**
 * 게시글 교정 관련 API를 제공하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/proofreads")
public class PostProofreadController {

    private final PostProofreadService postProofreadService;

    /**
     * 게시글 교정을 생성합니다.
     *
     * @param postProofreadCreateRequestDto 게시글 교정 생성 요청 DTO
     * @param userPrincipal                 인증된 사용자 정보
     * @return 생성된 게시글 교정 정보
     */
    @PostMapping
    public ApiResponse<PostProofreadCreateResponseDto> proofreadPost(
            @Valid @RequestBody PostProofreadCreateRequestDto postProofreadCreateRequestDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        PostProofreadCreateResponseDto postProofreadCreateResponseDto =
                postProofreadService.createProofread(postProofreadCreateRequestDto,
                        userPrincipal.getMemberId());
        return ApiResponse.success(postProofreadCreateResponseDto);
    }

    /**
     * 교정된 글을 게시글로 발행합니.
     *
     * @param taskUuid                       게시글 교정 세션의 UUID
     * @param postProofreadPublishRequestDto 게시글 교정 발행 요청 DTO
     * @param userPrincipal                  인증된 사용자 정보
     * @return 발행된 게시글 정보
     */
    @PostMapping("/{taskUuid}/posts")
    public ApiResponse<PostCreateResponseDto> publishProofreadPost(
            @PathVariable String taskUuid,
            @Valid @RequestBody PostProofreadPublishRequestDto postProofreadPublishRequestDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        PostCreateResponseDto postCreateResponseDto =
                postProofreadService.publishProofread(taskUuid,
                        postProofreadPublishRequestDto, userPrincipal.getMemberId());
        return ApiResponse.success(postCreateResponseDto);
    }
}