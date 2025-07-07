package today.sesac.shoutify.profanity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 비속어 수정 요청 Dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ProfanityUpdateRequestDto {

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
