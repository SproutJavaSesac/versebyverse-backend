package today.sesac.shoutify.comment.entity;

import jakarta.persistence.*;
import today.sesac.shoutify.global.domain.BaseEntityOnlyCreatedAt;

@Entity
public class Comment extends BaseEntityOnlyCreatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5000, nullable = false)
    private String contents;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private boolean isDeleted;

    @Column(nullable = false)
    private boolean isReported;
}
