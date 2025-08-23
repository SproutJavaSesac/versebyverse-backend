package today.sesac.versebyverse.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.member.entity.Member;

/**
 * 작업(Task)의 상태와 메타데이터 관리하는 도메인입니다.
 *
 *
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostProofreadTask extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String uuid; // 클라이언트와 주고받을 고유 ID

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 작업을 요청한 사용자

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status; // IN_PROGRESS, COMPLETED, ABANDONED

    @Enumerated(EnumType.STRING)
    private Concept concept; // 게시글의 컨셉 타입

    @Column(columnDefinition = "TEXT")
    private String initialTitle; // 사용자가 최초 입력한 원본 제목 (A)

    @Column(columnDefinition = "TEXT")
    private String initialContent; // 사용자가 최초 입력한 원본 글 (A)

    @OneToOne // 최종 선택된 결과물
    @JoinColumn(name = "final_attempt_id")
    private PostProofreadAttempt finalAttempt;

    private PostProofreadTask(
            String uuid,
            Member member,
            Concept concept,
            String initialTitle,
            String initialContent
    ) {

        this.uuid = uuid;
        this.member = member;
        this.status = TaskStatus.IN_PROGRESS; // 초기 상태는 IN_PROGRESS
        this.concept = concept;
        this.initialTitle = initialTitle;
        this.initialContent = initialContent;
        this.finalAttempt = null;
    }

    public static PostProofreadTask createProofreadTask(
            String uuid,
            Member member,
            Concept concept,
            String initialTitle,
            String initialContent
    ) {

        return new PostProofreadTask(uuid, member, concept, initialTitle, initialContent);
    }

    /**
     * 게시글 교정 작업을 완료합니다.
     *
     * @param finalAttempt 최종 선택된 교정 결과물
     *
     */
    public void complete(PostProofreadAttempt finalAttempt) {

        this.status = TaskStatus.COMPLETED;
        this.finalAttempt = finalAttempt;
    }
}