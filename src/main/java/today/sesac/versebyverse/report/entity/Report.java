package today.sesac.versebyverse.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.domain.BaseEntity;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 사용자가 게시글 또는 댓글을 신고할 때 사용되는 도메인입니다. 게시글과 댓글 중 하나만 신고 대상이 될 수 있습니다.
 */
@Entity
@Table(name = "reports")
@Check(constraints = "(post_id IS NOT NULL AND comment_id IS NULL) OR (post_id IS NULL AND comment_id IS NOT NULL)")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Report extends BaseEntity {

    /**
     * 신고의 고유 식별자입니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 신고를 한 회원입니다.
     */
    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private Member reporter;

    /**
     * 신고된 게시글입니다. 댓글 신고 시에는 null입니다.
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 신고된 댓글입니다. 게시글 신고 시에는 null입니다.
     */
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    /**
     * 신고 유형입니다. 게시글 또는 댓글 중 하나입니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType reportType;

    /**
     * 신고 사유입니다. 스팸, 부적절한 내용 등이 포함됩니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReasonType reasonType;

    /**
     * 신고 상세 사유입니다. 최대 500자까지 입력 가능합니다.
     */
    @Column(length = 500)
    private String reasonDetail;

    /**
     * 신고 처리 상태입니다. 기본값은 PENDING(대기중)입니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType statusType = StatusType.PENDING;

    /**
     * 신고를 생성하는 생성자입니다.
     *
     * @param reporter     신고를 한 회원
     * @param post         신고된 게시글 (댓글 신고 시 null)
     * @param comment      신고된 댓글 (게시글 신고 시 null)
     * @param reportType   신고 유형
     * @param reasonType   신고 사유
     * @param reasonDetail 신고 상세 사유
     */
    private Report(Member reporter, Post post, Comment comment,
            ReportType reportType, ReasonType reasonType, String reasonDetail) {

        this.reporter = reporter;
        this.post = post;
        this.comment = comment;
        this.reportType = reportType;
        this.reasonType = reasonType;
        this.reasonDetail = reasonDetail;
    }

    /**
     * 게시글 신고를 생성하는 정적 팩토리 메서드입니다.
     *
     * @param reporter     신고를 한 회원
     * @param post         신고된 게시글
     * @param reasonType   신고 사유
     * @param reasonDetail 신고 상세 사유
     * @return 생성된 Report 엔티티
     */
    public static Report createPostReport(Member reporter, Post post,
            ReasonType reasonType, String reasonDetail) {

        return new Report(reporter, post, null, ReportType.POST, reasonType, reasonDetail);
    }

    /**
     * 댓글 신고를 생성하는 정적 팩토리 메서드입니다.
     *
     * @param reporter     신고를 한 회원
     * @param comment      신고된 댓글
     * @param reasonType   신고 사유
     * @param reasonDetail 신고 상세 사유
     * @return 생성된 Report 엔티티
     */
    public static Report createCommentReport(Member reporter, Comment comment,
            ReasonType reasonType, String reasonDetail) {

        return new Report(reporter, null, comment, ReportType.COMMENT, reasonType, reasonDetail);
    }

    /**
     * 신고 상태를 승인으로 변경합니다.
     */
    public void accept() {

        this.statusType = StatusType.ACCEPTED;
    }

    /**
     * 신고 상태를 거부로 변경합니다.
     */
    public void reject() {

        this.statusType = StatusType.REJECTED;
    }

    /**
     * 신고 상태를 이미 삭제됨으로 변경합니다.
     */
    public void alreadyDeleted() {

        this.statusType = StatusType.ALREADY_DELETED;
    }

}
