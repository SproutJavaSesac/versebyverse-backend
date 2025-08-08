package today.sesac.versebyverse.profanity.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.profanity.entity.Profanity;

/**
 * 비속어 목록 응답을 감싸는 DTO.
 */
@AllArgsConstructor(staticName = "of")
@Getter
public class ProfanityListResponseWrapperDto {

    /**
     * 비속어 리스트.
     */
    private List<ProfanityResponseDto> profanities;

    /**
     * 페이지네이션 정보.
     */
    private PaginationDto pagination;

    /**
     * {@code Page<Profanity>}를 받아서 ProfanityListResponseWrapperDto를 생성하는 정적 팩토리 메서드.
     *
     * @param page 비속어 페이지 정보
     * @return ProfanityListResponseWrapperDto
     */
    public static ProfanityListResponseWrapperDto of(Page<Profanity> page) {

        List<ProfanityResponseDto> profanities = page.getContent().stream()
                .map(profanity -> ProfanityResponseDto.of(
                        profanity.getId(),
                        profanity.getOriginal(),
                        profanity.getReplacement(),
                        profanity.getDescription(),
                        profanity.getCategory()
                )).toList();

        PaginationDto pagination =
                new PaginationDto(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                        page.getSize(), page.hasNext(), page.hasPrevious());

        return new ProfanityListResponseWrapperDto(profanities, pagination);
    }

}