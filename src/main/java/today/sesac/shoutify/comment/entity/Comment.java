package today.sesac.shoutify.comment.entity;

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
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.post.entity.Post;

/**
 * 댓글 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    /**
     * 댓글 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * AI 변환 전 사용자가 입력한 원본 댓글
     */
    @Column(length = 500, nullable = false)
    private String beforeContent;

    /**
     * AI가 변환한 후의 댓글 내용
     */
    @Column(length = 500, nullable = false)
    private String afterContent;

    /**
     * 댓글이 작성된 게시물
     */
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    /**
     * 댓글 작성자
     */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    /**
     * 대댓글 기능 추가시 사용 예정
     */
    @Column(nullable = false)
    private int level;

    /**
     * 댓글 삭제 여부
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    /**
     * 댓글 신고 여부
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isReported;

    private Comment(String beforeContent, String afterContent, Post post, Member author) {
        this.beforeContent = beforeContent;
        this.afterContent = afterContent;
        this.post = post;
        this.author = author;
        this.level = 0;
        this.isDeleted = false;
        this.isReported = false;
    }

    public static Comment create(String beforeContent, String afterContent, Post post,
                                 Member author) {
        return new Comment(beforeContent, afterContent, post, author);
    }
}
