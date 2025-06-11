package today.sesac.shoutify.comment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntityOnlyCreatedAt;

@Entity
@NoArgsConstructor(access= AccessLevel.PRIVATE)
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
    private boolean isDeleted = false;

    @Column(nullable = false)
    private boolean isReported = false;

    private Comment(
            String contents,
            int score,
            int level
    ) {
        this.contents = contents;
        this.score = score;
        this.level = level;
    }

    public static Comment create(
            String contents,
            int score,
            int level
    ) {
        return new Comment(contents, score, level);
    }
}
