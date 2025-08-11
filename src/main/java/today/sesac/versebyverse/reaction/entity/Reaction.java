package today.sesac.versebyverse.reaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.domain.BaseEntity;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 반응하기 엔티티.
 */
@Entity //한 회원이 하나의 게시글 or 댓글에 대해 오직 하나의 반응만 가질 수 있도록 제약 (동시에 가지고 있는거 불가)
@Table(name = "member_reactions", uniqueConstraints = {
        @UniqueConstraint(name = "uk_member_post_reaction", columnNames = {"member_id", "post_id"}),
        @UniqueConstraint(name = "uk_member_comment_reaction", columnNames = {"member_id",
                "comment_id"})})
@Getter
@NoArgsConstructor
public class Reaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 다대일로 연결된 member 객체 .
     * 외래키 id 대신 객체 연관관계(객체 전체를 참조)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * 다대일로 연결된 post 객체 .
     * 외래키 id 대신 객체 연관관계(객체 전체를 참조)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 다대일로 연결된 comment 객체 .
     * 외래키 id 대신 객체 연관관계(객체 전체를 참조)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    /**
     * 감정 enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Emotion type;

    /**
     * Reaction 게시글 생성자.
     */
    private Reaction(Member author, Post post, Emotion emotion) {
        this.member = author;
        this.post = post;
        this.type = emotion;
    }

    /**
     * Reaction 댓글 생성자
     */
    private Reaction(Member author, Comment comment, Emotion emotion) {
        this.member = author;
        this.comment = comment;
        this.type = emotion;
    }

    /**
     * 게시글 반응 추가 정적 팩토리 메서드.
     */
    public static Reaction createPostReaction(Member author, Post post, Emotion emotion) {
        return new Reaction(author, post, emotion);
    }

    /**
     * 댓글 반응 추가 정적 팩토리 메서드.
     */
    public static Reaction createCommentReaction(Member author, Comment comment, Emotion emotion) {
        return new Reaction(author, comment, emotion);
    }

    /**
     * 게시글/댓글 유무 검증 메서드.
     * 하나의 회원에 게시글 또는 댓글 중 하나 반드시 있어야함
     */
    // TODO 에러 처리 임시로 설정 추후 변경 예정
    @PrePersist
    @PreUpdate
    private void validateReaction() {
        if (post == null && comment == null) {
            throw new IllegalArgumentException("게시글 또는 댓글 중 하나는 반드시 필요합니다.");
        }
        if (post != null && comment != null) {
            throw new IllegalArgumentException("게시글과 댓글에 동시에 반응할 수 없습니다.");
        }
    }
}
