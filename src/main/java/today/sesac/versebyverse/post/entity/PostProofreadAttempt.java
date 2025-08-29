package today.sesac.versebyverse.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * AI가 생성한 결과물(Attempt)을 저장하는 도메인입니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostProofreadAttempt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private PostProofreadTask task;

    @Column(nullable = false)
    private Emotion emotionType;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String afterContent; // AI가 생성한 내용

    @Column(nullable = false)
    private String afterTitle; // AI가 생성한 제목

    private PostProofreadAttempt(
            PostProofreadTask task,
            Emotion emotionType,
            String afterContent,
            String afterTitle
    ) {

        this.task = task;
        this.emotionType = emotionType;
        this.afterContent = afterContent;
        this.afterTitle = afterTitle;
    }

    /**
     * AI가 생성한 결과물(Attempt)을 생성하는 정적 팩토리 메서드입니다.
     *
     * @param task    작업(Task) 엔티티
     * @param emotion 감정(Emotion) 타입
     * @param title   AI가 생성한 제목
     * @param content AI가 생성한 내용
     * @return 생성된 PostProofreadAttempt 객체
     */
    public static PostProofreadAttempt createProofreadAttempt(PostProofreadTask task,
            Emotion emotion, String title,
            String content) {

        return new PostProofreadAttempt(
                task,
                emotion,
                content,
                title
        );
    }
}