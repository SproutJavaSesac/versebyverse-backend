package today.sesac.versebyverse.profanity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;
import today.sesac.versebyverse.profanity.dto.request.ProfanityUpdateRequestDto;
import today.sesac.versebyverse.profanity.exception.ProfanityErrorCode;
import today.sesac.versebyverse.profanity.exception.ProfanityException;

/**
 * {@code Profanity} 엔티티는 비속어 정보를 관리하기 위한 도메인입니다.
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
    @NotNull
    @Column(length = 50)
    private String original;

    /**
     * 비속어 원문에 대한 대체어입니다.
     */
    @Column(length = 20)
    private String replacement;

    /**
     * 비속어에 대한 설명입니다.
     */
    @Column(length = 500)
    private String description;

    /**
     * 비속어 카테고리를 나타냅니다. 다음 중 하나의 {@link ProfanityCategory} 값을 가집니다.
     * <ul>
     *     <li>{@code GENERAL_SWEAR} - 일반적인 욕설</li>
     *     <li>{@code SEXUAL_DEGRADATION} - 성적 발언</li>
     *     <li>{@code DISCRIMINATION_HATE} - 차별/혐오 표현</li>
     *     <li>{@code MODIFIED_SWEAR} - 우회적/비표준 표현</li>
     * </ul>
     */
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfanityCategory category;

    private Profanity(String original, String replacement, String description,
            ProfanityCategory category) {

        this.original = original;
        this.replacement = replacement;
        this.description = description;
        this.category = category;
    }

    /**
     * 지정된 원래 단어, 대체 단어, 설명 및 카테고리로 새로운 {@code Profanity} 인스턴스를 생성합니다.
     *
     * @param original    비속어 원문
     * @param replacement 대체어
     * @param description 설명
     * @param category    비속어 분류 카테고리
     * @return 주어진 매개변수로 초기화된 새로운 {@link Profanity} 객체입니다.
     */
    public static Profanity create(String original,
            String replacement,
            String description,
            ProfanityCategory category) {

        return new Profanity(original, replacement, description, category);
    }

    /**
     * 전달받은 요청 DTO의 값으로 Profanity 엔티티를 부분 업데이트합니다. null 또는 빈 문자열("") 값은 무시되며 기존 값이 유지됩니다.
     *
     * @param updateRequestDto 수정할 값이 담긴 요청 DTO
     */
    public void updateProfanity(ProfanityUpdateRequestDto updateRequestDto) {

        if (updateRequestDto.getOriginal().equals(original)
                && updateRequestDto.getReplacement().equals(replacement)
                && updateRequestDto.getDescription().equals(description)
                && updateRequestDto.getCategory().equals(category)) {
            throw new ProfanityException(ProfanityErrorCode.PROFANITY_NO_CHANGES,
                    String.valueOf(this.id));
        } else {
            this.original = updateRequestDto.getOriginal();
            this.replacement = updateRequestDto.getReplacement();
            this.description = updateRequestDto.getDescription();
            this.category = updateRequestDto.getCategory();
        }
    }
}