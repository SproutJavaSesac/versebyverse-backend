package today.sesac.versebyverse.profanity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import today.sesac.versebyverse.profanity.entity.ProfanityCategory;

/**
 * 비속어 등록 요청 DTO.
 */
public record ProfanityRegisterRequestDto(
        @NotBlank
        @Size(max = 20)
        String original,
        @Size(max = 20)
        String replacement,
        @Size(max = 500)
        String description,
        @NotNull
        ProfanityCategory category
) {

}