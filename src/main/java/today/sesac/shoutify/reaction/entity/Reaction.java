package today.sesac.shoutify.reaction.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;
import today.sesac.shoutify.post.entity.Post;

@Entity
@Getter
/**
 * 복합 유니크 제약조건
 * {user_id,post_id},{user_id,comment_id} 조합이 중복으로 있을 수 없음
 * 한 사람이 같은 게시물이나 댓글에 다수의 반응을 달 수 없음
 */
//@Table(name = "reactions", uniqueConstraints =
//        {@UniqueConstraint(name = "uk_user-post", columnNames = {"user_id", "post_id"}),
//                @UniqueConstraint(name = "uk_user-comment", columnNames = {"user_id", "comment_id"})})

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne //일대다 관계
    @JoinColumn(name = "post_id")
    private Post post;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private Comment comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Emogi emogi;

}
