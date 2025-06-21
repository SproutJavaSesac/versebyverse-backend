package today.sesac.shoutify.comment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;
import today.sesac.shoutify.post.entity.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * AI 변환 전 사용자가 입력한 원본 댓글
	 */
	@Column(length = 500, nullable = false)
	private String beforeContents;

	/**
	 * AI가 변환한 후의 댓글 내용
	 */
	@Column(length = 500, nullable = false)
	private String afterContents;

	/**
	 * 댓글이 작성된 게시물
	 */
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	// @ManyToOne
	// @JoinColumn(name = "member_id")
	// private Member author;

	/**
	 * TODO: 대댓글 기능 추가 예정
	 */
	@Column(nullable = false)
	private int level;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean isDeleted = false;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean isReported = false;

	private Comment(String beforeContents, String afterContents, Post post) {
		this.beforeContents = beforeContents;
		this.afterContents = afterContents;
		this.post = post;
		this.level = 0;
	}

	public static Comment create(String beforeContents, String afterContents, Post post) {
		return new Comment(beforeContents, afterContents, post);
	}
}

