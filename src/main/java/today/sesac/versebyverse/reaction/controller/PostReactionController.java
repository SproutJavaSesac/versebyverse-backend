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
import today.sesac.versebyverse.reaction.dto.request.ReactionRequestDto;
import today.sesac.versebyverse.reaction.dto.response.ReactionResponseDto;
import today.sesac.versebyverse.reaction.service.ReactionService;
import today.sesac.versebyverse.reaction.utils.TargetType;

/**
 * 게시물 반응 관련 컨트롤러.
 */
@RestController
@RequestMapping("/api/v1/posts/{postId}")
@RequiredArgsConstructor
public class PostReactionController {
    private final ReactionService reactionService;

    /**
     * 반응하기 조회.
     *
     * @param postId 게시글 id
     */

    @GetMapping("/reactions")
    public ApiResponse<ReactionResponseDto> getPostReaction(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();

        return ApiResponse.success(
                reactionService.getReactions(TargetType.POST, postId, memberId));
    }

    /**
     * 게시물에 반응 추가하기.
     *
     * @param postId 반응을 추가할 게시글 id
     */

    @PostMapping("/reactions")
    public ApiResponse<ReactionResponseDto> addPostEmotion(
            @PathVariable Long postId,
            @RequestBody ReactionRequestDto reactionRequestDto) {
        Long memberId = 1L; // TODO: 현재 사용자 memberId 1로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                reactionService.addReaction(reactionRequestDto, postId, memberId,
                        TargetType.POST));
    }

    /**
     * 게시글 반응 삭제하기.
     *
     * @param postId 반응을 삭제할 게시글 id
     * @param type   삭제할 반응 타입
     */
    @DeleteMapping("/reactions/{type}")
    public ApiResponse<ReactionResponseDto> deleteEmotion(
            @PathVariable Long postId, @PathVariable String type) {
        Long memberId = 2L; // TODO: 현재 사용자 memberId 2로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                reactionService.deleteReaction(type, postId, memberId, TargetType.POST));
    }

    /**
     * 게시물 반응 수정하기
     *
     * @param postId             게시물 id
     * @param reactionRequestDto 추가될 감정 dto
     */
    @PatchMapping("/reactions")
    public ApiResponse<ReactionResponseDto> updateEmotion(
            @PathVariable Long postId,
            @RequestBody ReactionRequestDto reactionRequestDto) {
        Long memberId = 2L; // TODO: 현재 사용자 memberId 1로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                reactionService.updateReaction(TargetType.POST, postId, reactionRequestDto,
                        memberId));
    }


}