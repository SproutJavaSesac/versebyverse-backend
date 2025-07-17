package today.sesac.versebyverse.profanity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 비속어 등록 요청 DTO.
 */
@Getter
public class ProfanityRegisterRequestDto {

    /**
     * 비속어 (20자 이하, 필수).
     */
    @NotBlank
    @Size(max = 20)
    private String original;

    /**
     * 대체어 (20자 이하, 선택).
     */
    @Size(max = 20)
    private String replacement;

    /**
     * 등록 이유 (500자 이하, 선택).
     */
    @Size(max = 500)
    private String description;

    /**
     * 비속어 카테고리 (필수).
     */
    @NotBlank
    private String category;
}