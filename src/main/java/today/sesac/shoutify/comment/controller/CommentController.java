package today.sesac.shoutify.comment.controller;

import org.springframework.http.ResponseEntity;
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
}
