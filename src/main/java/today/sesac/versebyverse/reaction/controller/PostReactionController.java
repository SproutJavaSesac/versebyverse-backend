package today.sesac.versebyverse.reaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.reaction.dto.request.PostReactionRequestDto;
import today.sesac.versebyverse.reaction.dto.response.PostReactionResponseDto;
import today.sesac.versebyverse.reaction.service.PostReactionService;

/**
 * 게시물 반응 관련 컨트롤러.
 */
@RestController
@RequestMapping("/api/v1/posts/{postId}/reactions")
@RequiredArgsConstructor
public class PostReactionController {
    private final PostReactionService postReactionService;

    /**
     * 게시물에 반응 추가하기.
     *
     * @param postId 반응을 추가할 게시글 id
     */

    @PostMapping
    public ApiResponse<PostReactionResponseDto> addEmotion(
            @PathVariable Long postId, @RequestBody PostReactionRequestDto postReactionRequestDto) {
        Long memberId = 1L; // TODO: 현재 사용자 memberId 1로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                postReactionService.addPostReaction(postReactionRequestDto, postId, memberId));
    }

    /**
     * 게시글 반응 삭제하기.
     *
     * @param postId 반응을 삭제할 게시글 id
     * @param type   삭제할 반응 타입
     */
    @DeleteMapping("/{type}")
    public ApiResponse<PostReactionResponseDto> deleteEmotion(
            @PathVariable Long postId, @PathVariable String type) {
        Long memberId = 2L; // TODO: 현재 사용자 memberId 2로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                postReactionService.deletePostReaction(type, postId, memberId));
    }

    /**
     * 게시물 반응 수정하기
     *
     * @param postId                 게시물 id
     * @param postReactionRequestDto 추가될 감정 dto
     */
    @PatchMapping
    public ApiResponse<PostReactionResponseDto> updateEmotion(
            @PathVariable Long postId, @RequestBody PostReactionRequestDto postReactionRequestDto) {
        Long memberId = 2L; // TODO: 현재 사용자 memberId 1로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                postReactionService.updatePostReaction(postReactionRequestDto, postId, memberId));
    }
}