package today.sesac.versebyverse.reaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.service.UserPrincipal;
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
     * @return 반응하기 응답 dto
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
     * @return 반응하기 응답 dto
     */

    @PostMapping("/reactions")
    public ApiResponse<ReactionResponseDto> addPostEmotion(
            @PathVariable Long postId,
            @RequestBody ReactionRequestDto reactionRequestDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        return ApiResponse.success(
                reactionService.addReaction(reactionRequestDto, postId, memberId,
                        TargetType.POST));
    }

    /**
     * 게시글 반응 삭제하기.
     *
     * @param postId 반응을 삭제할 게시글 id
     * @param type   삭제할 반응 타입
     * @return 문자열
     */
    @DeleteMapping("/reactions/{type}")
    public ApiResponse<String> deleteEmotion(
            @PathVariable Long postId, @PathVariable String type,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        reactionService.deleteReaction(type, postId, memberId, TargetType.POST);
        return ApiResponse.success("게시글의 반응이 성공적으로 삭제됐습니다");
    }

    /**
     * 게시물 반응 수정하기.
     *
     * @param postId             게시물 id
     * @param reactionRequestDto 추가될 감정 dto
     * @return 반응하기 응답 dto
     */
    @PutMapping("/reactions")
    public ApiResponse<ReactionResponseDto> updateEmotion(
            @PathVariable Long postId,
            @RequestBody ReactionRequestDto reactionRequestDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        return ApiResponse.success(
                reactionService.updateReaction(TargetType.POST, postId, reactionRequestDto,
                        memberId));
    }
}
