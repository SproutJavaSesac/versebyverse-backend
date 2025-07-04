package today.sesac.shoutify.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.post.entity.Post;

/**
 * 댓글 엔티티.
 *
 * <p>정적 메서드 {@link #createFirstLevelComment(String, String, Post, Member)}를 통해 생성합니다.</p>
 */
@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    /**
     * 댓글 고유 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 댓글이 작성된 게시물.
     */
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    /**
     * 댓글 작성자.
     */
    @ManyToOne
    @JoinColumn(name = "commenter_id", nullable = false)
    private Member commenter;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    /**
     * AI 변환 전 사용자가 입력한 원본 댓글.
     */
    @Column(length = 500, nullable = false)
    private String beforeContent;

    /**
     * AI가 변환한 후의 댓글 내용.
     */
    @Column(length = 500, nullable = false)
    private String afterContent;

    /**
     * 댓글의 계층 레벨.
     */
    @Column(nullable = false)
    private int level;

    /**
     * 댓글 삭제 여부.
     */
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    /**
     * 신고된 댓글인지 여부.
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isReported;

    /**
     * 댓글에 대한 답글 목록.
     */
    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY)
    private List<Comment> replies = new ArrayList<>();

    private Comment(String beforeContent, String afterContent, int level, Post post,
            Comment parentComment,
            Member commenter) {

        this.beforeContent = beforeContent;
        this.afterContent = afterContent;
        this.post = post;
        this.commenter = commenter;
        this.parentComment = parentComment;
        this.level = level;
        this.isDeleted = false;
        this.isReported = false;
    }

    /**
     * 첫 번째 레벨 댓글을 생성합니다.
     *
     * @param beforeContent AI 변환 전 사용자가 입력한 원본 댓글 내용
     * @param afterContent  AI가 변환한 후의 댓글 내용
     * @param post          댓글 다는 게시물
     * @param commenter     댓글 작성자
     * @return 새로운 첫 번째 레벨 댓글 엔티티
     */
    public static Comment createFirstLevelComment(String beforeContent, String afterContent,
            Post post,
            Member commenter) {

        return new Comment(beforeContent, afterContent, 0, post, null, commenter);
    }
}
