package today.sesac.shoutify.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.comment.dto.request.CommentCreateRequestDto;
import today.sesac.shoutify.comment.dto.response.CommentCreateResponseDto;
import today.sesac.shoutify.comment.dto.response.CommentListResponseDto;
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

    /**
     * 게시글의 전체 댓글을 페이지네이션 방식으로 조회합니다.
     *
     * @param postId 게시글 ID
     * @param page   페이지 번호 (0부터 시작)
     * @param size   페이지 크기 (기본값: 10)
     * @return 댓글 목록 응답 DTO
     */
    @GetMapping
    public ApiResponse<CommentListResponseDto> getComments(@PathVariable("postId") Long postId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        CommentListResponseDto commentListResponseDto = commentService.getCommentsByPostId(postId,
                PageRequest.of(page, size));

        return ApiResponse.success(commentListResponseDto);
    }

    /**
     * 특정 댓글을 삭제합니다.
     *
     * @param commentId 삭제할 댓글의 ID
     * @return 성공 메시지
     */
    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(
            @PathVariable("commentId") Long commentId) {

        commentService.deleteComment(commentId);
        return ApiResponse.success("성공적으로 댓글을 삭제했습니다.");
    }
}
