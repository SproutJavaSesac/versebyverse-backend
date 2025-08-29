package today.sesac.versebyverse.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import today.sesac.versebyverse.global.domain.BaseEntity;
import today.sesac.versebyverse.global.domain.Genre;
import today.sesac.versebyverse.member.entity.Member;

/**
 * 작업(Task)의 상태와 메타데이터 관리하는 도메인입니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostProofreadTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid; // 클라이언트와 주고받을 고유 ID

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'IN_PROGRESS'")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genreType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String initialTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String initialContent;

    @OneToOne
    @JoinColumn(name = "final_attempt_id")
    private PostProofreadAttempt finalAttempt;

    private PostProofreadTask(
            String uuid,
            Member member,
            Genre genreType,
            String initialTitle,
            String initialContent
    ) {

        this.uuid = uuid;
        this.member = member;
        this.status = TaskStatus.IN_PROGRESS;
        this.genreType = genreType;
        this.initialTitle = initialTitle;
        this.initialContent = initialContent;
        this.finalAttempt = null;
    }

    /**
     * 게시글 첨삭 작업을 생성하는 정적 팩토리 메서드입니다.
     *
     * @param member         작업을 요청한 회원
     * @param genre          게시글의 컨셉 장르
     * @param initialTitle   사용자가 최초 입력한 원본 제목
     * @param initialContent 사용자가 최초 입력한 원본 글
     * @return 생성된 PostProofreadTask 객체
     */
    public static PostProofreadTask createProofreadTask(
            Member member,
            Genre genre,
            String initialTitle,
            String initialContent
    ) {

        String newUuid = UUID.randomUUID().toString();

        return new PostProofreadTask(newUuid, member, genre, initialTitle, initialContent);
    }

    /**
     * 게시글 첨삭 작업을 완료합니다.
     *
     * @param finalAttempt 최종 선택된 첨삭 결과물
     *
     */
    public void complete(PostProofreadAttempt finalAttempt) {

        this.status = TaskStatus.COMPLETED;
        this.finalAttempt = finalAttempt;
    }
}