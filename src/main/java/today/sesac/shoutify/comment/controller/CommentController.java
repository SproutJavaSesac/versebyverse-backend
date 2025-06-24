package today.sesac.shoutify.comment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import today.sesac.shoutify.comment.dto.CommentRequest;
import today.sesac.shoutify.comment.dto.CommentResponse;
import today.sesac.shoutify.comment.service.CommentService;
import today.sesac.shoutify.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {

	private final CommentService commentService;

	/**
	 * 특정 게시글에 댓글을 작성합니다.
	 *
	 * @param postId  댓글을 작성할 게시글의 ID
	 * @param request 댓글 내용, 작성자 ID, 대댓글 여부를 포함하는 요청 객체
	// * @param currentUser 현재 로그인한 사용자 정보
	 * @return 댓글 작성 성공 여부 및 메시지
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<CommentResponse>> createComment(
		@PathVariable Long postId,
		// @AuthenticationPrincipal CurrentUser currentUser,
		@Valid @RequestBody CommentRequest request
	) {
		// Long authorId = currentUser.getMemberId();
		Long authorId = 1L;

		CommentResponse response = commentService.createComment(postId, authorId, request);

		return ResponseEntity.ok(ApiResponse.success(response));
	}

	/**
	 * 특정 게시글의 댓글 목록을 조회합니다.
	 *
	 * @param postId 댓글을 조회할 게시글의 ID
	 * @return 댓글 목록
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<CommentResponse>>> getComments(
		@PathVariable Long postId
	) {
		List<CommentResponse> comments = commentService.getCommentsByPostId(postId);

		return ResponseEntity.ok(ApiResponse.success(comments));
	}

	/**
	 * 댓글을 삭제합니다.
	 *
	 * @param postId    게시글 ID
	 * @param commentId 삭제할 댓글 ID
	 * @return 삭제 성공 메시지
	 */
	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse<String>> deleteComment(
		@PathVariable Long postId,
		@PathVariable Long commentId
	) {
		commentService.deleteComment(postId, commentId);

		return ResponseEntity.ok(ApiResponse.success("댓글이 삭제되었습니다."));
	}
}
