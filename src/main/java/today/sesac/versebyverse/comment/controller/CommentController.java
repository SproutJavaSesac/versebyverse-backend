package today.sesac.versebyverse.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.comment.dto.request.CommentCreateRequestDto;
import today.sesac.versebyverse.comment.dto.response.CommentCreateResponseDto;
import today.sesac.versebyverse.comment.dto.response.CommentListResponseDto;
import today.sesac.versebyverse.comment.dto.response.CommentSingleQueryForAdminResponseDto;
import today.sesac.versebyverse.comment.service.CommentService;
import today.sesac.versebyverse.global.response.ApiResponse;

/**
 * CommentController는 댓글 관련 API를 처리하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/v1")
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
    @PostMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CommentCreateResponseDto> createComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto,
            @PathVariable("postId") Long postId) {

        Long memberId = userPrincipal.getMemberId();

        CommentCreateResponseDto commentCreateResponseDto = commentService.writeComment(
                commentCreateRequestDto, memberId, postId);

        return ApiResponse.success(commentCreateResponseDto);
    }

    /**
     * 특정 댓글을 조회합니다.
     *
     * @param postId 게시글 ID
     * @return 댓글 응답 DTO
     */
    @GetMapping("/admin/posts/{postId}/comments/{commentId}")
    public ApiResponse<CommentSingleQueryForAdminResponseDto> getCommentForAdmin(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId) {

        CommentSingleQueryForAdminResponseDto commentSingleQueryForAdminResponseDto =
                commentService.getCommentByIdForAdmin(postId, commentId);

        return ApiResponse.success(commentSingleQueryForAdminResponseDto);
    }

    /**
     * 게시글의 전체 댓글을 페이지네이션 방식으로 조회합니다.
     *
     * @param postId 게시글 ID
     * @param page   페이지 번호 (0부터 시작)
     * @param size   페이지 크기 (기본값: 10)
     * @return 댓글 목록 응답 DTO
     */
    @GetMapping("/posts/{postId}/comments")
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
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ApiResponse<String> deleteComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable("commentId") Long commentId) {

        Long memberId = userPrincipal.getMemberId();

        commentService.deleteComment(commentId, memberId);
        return ApiResponse.success("성공적으로 댓글을 삭제했습니다.");
    }
}