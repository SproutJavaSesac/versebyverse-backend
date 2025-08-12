package today.sesac.versebyverse.profanity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.profanity.entity.ProfanityCategory;

/**
 * 비속어 수정 요청 Dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ProfanityUpdateRequestDto {

    /**
     * 비속어 원문.
     */
    @NotBlank
    @Size(max = 20)
    private String original;

    /**
     * 대체어 (순화된 표현).
     */
    @Size(max = 20)
    private String replacement;

    /**
     * 비속어 설명.
     */
    @Size(max = 500)
    private String description;

    /**
     * 비속어 카테고리.
     */
    @NotNull
    private ProfanityCategory category;
}