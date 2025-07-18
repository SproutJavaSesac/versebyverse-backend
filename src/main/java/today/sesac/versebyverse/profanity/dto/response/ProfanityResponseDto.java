package today.sesac.versebyverse.profanity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 비속어 정보 DTO.
 */
@Data
@Builder
@AllArgsConstructor(staticName = "of")
public class ProfanityResponseDto {

    /**
     * 비속어 식별자.
     */
    private int profanityId;

    /**
     * 비속어 원문.
     */
    private String original;

    /**
     * 대체어 (순화된 표현).
     */
    private String replacement;

    /**
     * 비속어 설명.
     */
    private String description;

    /**
     * 비속어 카테고리.
     */
    private String category;
}