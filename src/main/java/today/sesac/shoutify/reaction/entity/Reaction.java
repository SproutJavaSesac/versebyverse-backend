package today.sesac.shoutify.reaction.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;
import today.sesac.shoutify.post.entity.Post;

/**
 * 댓글과 다른 사용자의 게시물에 대한 반응하기 엔티티입니다.
 */
@Entity
@Getter
/**
 * 복합 유니크 제약조건
 * {user_id,post_id},{user_id,comment_id} 조합이 중복으로 있을 수 없음
 * 한 사람이 같은 게시물이나 댓글에 다수의 반응을 달 수 없음
 */
@Table(name = "reactions")
//@Table(name = "reactions", uniqueConstraints =
//        {@UniqueConstraint(name = "uk_author-post", columnNames = {"author", "post_id"}),
//                @UniqueConstraint(name = "uk_author-comment", columnNames = {"author", "comment_id"})})

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    /**
     * 해당 반응이 달린 게시물의 id
     */
    @ManyToOne //일대다 관계
    @JoinColumn(name = "post_id")
    private Post post;
    /**
     * 해당 반응을 표시한 유저 id
     */
//    @ManyToOne
//    @JoinColumn(name = "author", nullable = false)
//    private User user;
    /**
     * 해당 반응이 달린 댓글id
     */
//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private Comment comment;
    /**
     * enum 타입으로 정의된 이모지
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Emogi emogi;

}
