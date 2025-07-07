package today.sesac.shoutify.profanity.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import today.sesac.shoutify.global.response.PaginationDto;

/**
 * 비속어 목록 응답을 감싸는 DTO.
 */
@Data
@Builder
public class ProfanityListResponseWrapperDto {

    /**
     * 비속어 리스트.
     */
    private List<ProfanityResponseDto> profanities;

    /**
     * 페이지네이션 정보.
     */
    private PaginationDto pagination;
    
}