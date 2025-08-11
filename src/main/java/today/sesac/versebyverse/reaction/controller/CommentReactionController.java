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

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments/{commentId}/reactions")
@RequiredArgsConstructor
public class CommentReactionController {

    private final ReactionService reactionService;

    @PostMapping
    public ApiResponse<ReactionResponseDto> addCommentReaction(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody ReactionRequestDto reactionRequestDto) {
        Long memberId = 2L; // TODO: 현재 사용자 memberId 2로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                reactionService.addReaction(reactionRequestDto, commentId, memberId,
                        TargetType.COMMENT));
    }

    @DeleteMapping("/{type}")
    public ApiResponse<ReactionResponseDto> deleteCommentReaction(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @PathVariable String type) {
        Long memberId = 2L; // TODO: 현재 사용자 memberId 2로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                reactionService.deleteReaction(type, commentId, memberId, TargetType.COMMENT));
    }

    @PatchMapping
    public ApiResponse<ReactionResponseDto> updateCommentReaction(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody ReactionRequestDto reactionRequestDto) {
        Long memberId = 2L; // TODO: 현재 사용자 memberId 2로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                reactionService.updateReaction(TargetType.COMMENT, commentId, reactionRequestDto,
                        memberId));
    }
}
