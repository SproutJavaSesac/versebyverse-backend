package today.sesac.shoutify.comment.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import today.sesac.shoutify.comment.entity.Comment;

@Getter
public class CommentResponse {

	private Long id;
	private Long postId;
	private Long authorId;
	private String authorName; // 작성자명 추가
	private Long parentId;
	private String content;
	private int likeCount;
	private boolean isDeleted;
	private boolean isReported;
	private LocalDateTime createdAt;
	private int level;
	private List<CommentResponse> replies;

	@Builder
	public CommentResponse(Long id, Long postId, Long authorId, String authorName, Long parentId,
		String content, int likeCount, boolean isDeleted, boolean isReported,
		LocalDateTime createdAt, int level, List<CommentResponse> replies) {
		this.id = id;
		this.postId = postId;
		this.authorId = authorId;
		this.authorName = authorName;
		this.parentId = parentId;
		this.content = content;
		this.likeCount = likeCount;
		this.isDeleted = isDeleted;
		this.isReported = isReported;
		this.createdAt = createdAt;
		this.level = level;
		this.replies = replies != null ? replies : Collections.emptyList();
	}

	public static CommentResponse from(Comment comment) {
		return CommentResponse.builder()
			.id(comment.getId())
			.postId(comment.getPost().getId())
			.authorId(comment.getAuthor().getId())
			.authorName(comment.getAuthor().getNickname())
			.parentId(null) // 현재 대댓글 기능 미사용
			.content(comment.getAfterContent()) // AI 변환된 내용 사용
			.likeCount(0) // 현재 좋아요 기능 미구현
			.isDeleted(comment.isDeleted())
			.isReported(comment.isReported())
			.createdAt(comment.getCreatedAt())
			.level(comment.getLevel())
			.replies(Collections.emptyList()) // 현재 대댓글 미사용
			.build();
	}
}
