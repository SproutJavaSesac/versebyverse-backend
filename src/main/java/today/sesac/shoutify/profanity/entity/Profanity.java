package today.sesac.shoutify.profanity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import today.sesac.shoutify.global.domain.BaseEntity;

/**
 * {@code Profanity} 엔티티는 비속어 정보를 관리하기 위한 도메인입니다.
 * <p>
 * 관리자(admin) 페이지를 통해 비속어의 등록, 수정, 삭제, 조회 기능을 제공합니다.
 * </p>
 * <p><b>주요 필드</b>
 * - original: 원문 비속어<br>
 * - replacement: 대체어 (순화 표현)<br>
 * - description: 비속어에 대한 설명<br>
 * - severity: 심각도 등급<br>
 * - category: 카테고리(욕설, 성적, 혐오 등)
 */
@Entity
@Getter
@Table(name = "profanities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profanity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 비속어 원문을 나타냅니다.
     */
    @Column(nullable = false, length = 20, unique = true)
    private String original;
    @Column(length = 20)
    private String replacement;
    @Column(length = 500)
    private String description;

    /**
     * 관리자가 심각도를 지정하며 기본 값으로 1을 가집니다
     */
    @ColumnDefault("1")
    private int severity;


    /**
     *  비속어 카테고리를 나타냅니다.
     *  다음 중 하나의 {@link ProfanityCategory} 값을 가집니다.
     *  <ul>
     *      <li>{@code GENERAL_SWEAR} - 일반적인 욕설</li>
     *      <li>{@code SEXUAL_DEGRADATION} - 성적 발언</li>
     *      <li>{@code DISCRIMINATION_HATE} - 차별/혐오 표현</li>
     *      <li>{@code MODIFIED_SWEAR} - 우회적/비표준 표현</li>
     *  </ul>
     */
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfanityCategory category;

    private Profanity(String original, String replacement, String description, int severity, ProfanityCategory category){
        this.original = original;
        this.replacement = replacement;
        this.description = description;
        this.severity = severity;
        this.category = category;
    }

    public static Profanity of(String original,
            String replacement,
            String description,
            int severity,
            ProfanityCategory category) {

       return new Profanity(original, replacement, description,severity, category);
    }


}
