package today.sesac.versebyverse.profanity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import today.sesac.versebyverse.profanity.entity.ProfanityCategory;

/**
 * 비속어 정보 DTO.
 */
@Builder        //TODO: 삭제예정
@Getter
@AllArgsConstructor(staticName = "of")
public class ProfanityResponseDto {

    /**
     * 비속어 식별자.
     */
    private long profanityId;

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
    private ProfanityCategory category;
}