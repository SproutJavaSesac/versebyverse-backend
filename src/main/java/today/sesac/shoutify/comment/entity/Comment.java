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
import org.hibernate.annotations.SQLDelete;
import today.sesac.shoutify.global.domain.BaseEntity;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.post.entity.Post;

/**
 * 댓글 엔티티.
 * TODO {@code @softdelete}와 비교해 보기.
 *
 * <p>정적 메서드 {@link #createFirstLevelComment(String, String, Post, Member)}를 통해 생성합니다.</p>
 */
@Getter
@Entity
@Table(name = "comments")
@SQLDelete(sql = "UPDATE comments SET is_deleted = true WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    static final int MAX_REPLY_LEVEL = 2;

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

    @Column(length = 30, nullable = false)
    private String path;

    /**
     * 댓글 삭제 여부.
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    /**
     * 신고된 댓글인지 여부.
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isReported;

    private Comment(
            String beforeContent, String afterContent, int level,
            Post post, Comment parentComment, Member commenter) {

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
    public static Comment createFirstLevelComment(
            String beforeContent, String afterContent, Post post, Member commenter) {

        return new Comment(beforeContent, afterContent, 0, post, null, commenter);
    }

    /**
     * 댓글의 경로를 업데이트합니다.
     * TODO postpersist, entitylistener(pathlistener)를 사용하여 경로 자동 업데이트하는 방법 고려.
     *
     * <p>댓글이 최상위 댓글인 경우, 경로는 댓글 ID만 포함됩니다.
     * 답글인 경우, 부모 댓글의 경로에 현재 댓글 ID를 추가하여 경로를 생성합니다.</p>
     *
     * @return 업데이트된 경로
     */
    public String updatePath() {

        if (parentComment == null) {
            this.path = String.valueOf(id);
        } else {
            this.path = parentComment.getPath() + "-" + id;
        }
        return this.path;
    }

    /**
     * 화면에 표시할 내용을 반환합니다.
     */
    public String getDisplayContent() {

        if (isDeleted) {
            return "삭제된 내용입니다";
        }
        if (isReported) {
            return "신고된 내용입니다";
        }
        return afterContent;
    }

    /**
     * 화면에 표시할 작성자 ID를 반환합니다.
     */
    public Long getDisplayCommenterId() {

        if (isDeleted || isReported) {
            return null;
        }
        return commenter.getId();
    }

    /**
     * 화면에 표시할 작성자 닉네임을 반환합니다.
     */
    public String getDisplayCommenterNickname() {

        if (isDeleted) {
            return "알 수 없음";
        }
        if (isReported) {
            return "신고된 사용자";
        }
        return commenter.getNickname();
    }
}