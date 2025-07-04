package today.sesac.shoutify.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.comment.dto.request.CommentCreateRequestDto;
import today.sesac.shoutify.comment.dto.response.CommentCreateResponseDto;
import today.sesac.shoutify.comment.service.CommentService;
import today.sesac.shoutify.global.response.ApiResponse;

/**
 * CommentController는 댓글 관련 API를 처리하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 특정 게시글에 댓글을 작성합니다.
     *
     * @param commentCreateRequestDto 댓글 작성 요청 DTO
     * @param postId                  작성할 게시글의 ID
     * @return 댓글 작성 응답 DTO
     */
    @PostMapping
    public ApiResponse<CommentCreateResponseDto> createComment(
            @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto,
            @PathVariable("postId") Long postId) {

        Long memberId = 1L; // TODO: 현재 사용자 memberId 1로 하드코딩, 추후 변경 예정
        CommentCreateResponseDto commentCreateResponseDto = commentService.createComment(
                commentCreateRequestDto, memberId, postId);

        return ApiResponse.success(commentCreateResponseDto);
    }
}
