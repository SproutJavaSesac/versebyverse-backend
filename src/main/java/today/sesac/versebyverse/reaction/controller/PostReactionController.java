package today.sesac.versebyverse.reaction.controller;

import lombok.RequiredArgsConstructor;
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
     */

    @PostMapping
    public ApiResponse<PostReactionResponseDto> addEmotion(
            @PathVariable Long postId, @RequestBody PostReactionRequestDto postReactionRequestDto) {
        Long memberId = 1L; // TODO: 현재 사용자 memberId 1로 하드코딩, 추후 변경 예정
        return ApiResponse.success(
                postReactionService.addPostReaction(postReactionRequestDto, postId, memberId));
    }


}