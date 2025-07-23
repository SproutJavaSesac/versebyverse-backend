package today.sesac.versebyverse.profanity.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.profanity.dto.request.ProfanityRegisterRequestDto;
import today.sesac.versebyverse.profanity.dto.request.ProfanityUpdateRequestDto;
import today.sesac.versebyverse.profanity.dto.response.ProfanityListResponseWrapperDto;
import today.sesac.versebyverse.profanity.dto.response.ProfanityResponseDto;
import today.sesac.versebyverse.profanity.entity.ProfanityCategory;
import today.sesac.versebyverse.profanity.service.ProfanityService;

/**
 * 비속어 관련 API 컨트롤러.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/profanities")
@RequiredArgsConstructor
public class ProfanityController {

    private final ProfanityService profanityService;

    /**
     * 비속어 목록 조회 API입니다.
     *
     * @param page 현재 페이지
     * @param size 한 페이지에 나타낼 데이터 수
     * @param sort 정렬
     * @return 응답
     */
    @GetMapping
    public ApiResponse<ProfanityListResponseWrapperDto> profanity(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "sort", defaultValue = "latest") String sort) {

        List<ProfanityResponseDto> profanities = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            int id = ((page) * size) + i;
            profanities.add(
                    ProfanityResponseDto.builder()
                            .profanityId(id)
                            .original("비속어" + id)
                            .replacement("순화어" + id)
                            .description("설명" + id)
                            .category(ProfanityCategory.MODIFIED_SWEAR)
                            .build()
            );
        }

        PaginationDto pagination = new PaginationDto(
                page,
                size,
                1000,
                (int) Math.ceil(1000.0 / size),
                page * size < 1000,
                page > 0
        );

        ProfanityListResponseWrapperDto response = ProfanityListResponseWrapperDto.builder()
                .profanities(profanities)
                .pagination(pagination)
                .build();

        return ApiResponse.success(response);
    }

    /**
     * 비속어 등록 API.
     *
     * @param profanityRegisterRequestDto 클라이언트 입력 비속어등록 Dto
     * @return 응답
     */
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")  //Spring Security 권한 제어 어노테이션
    public ApiResponse<ProfanityResponseDto> registerProfanity(
            @RequestBody ProfanityRegisterRequestDto profanityRegisterRequestDto) {

        return ApiResponse.success(profanityService.registerProfanity(profanityRegisterRequestDto));
    }

    /**
     * 비속어 수정 API. 비속어 원문, 대체어, 설명, 카테고리를 수정 할 수 있습니다.
     *
     * @param profanityId 비속어 식별 id
     * @return 응답
     */
    @PatchMapping("{profanityId}")
    public ApiResponse<ProfanityResponseDto> updateProfanity(@PathVariable int profanityId,
            @RequestBody ProfanityUpdateRequestDto dto) {

        return ApiResponse.success(ProfanityResponseDto.of(
                profanityId, dto.getOriginal(), dto.getReplacement(), dto.getDescription(),
                dto.getCategory()
        ));
    }

    /**
     * 비속어 삭제 API.
     *
     * @param profanityId 비속어 식별 id
     * @return 응답
     */
    @DeleteMapping("{profanityId}")
    public ApiResponse<String> deleteProfanity(@PathVariable int profanityId) {

        return ApiResponse.success("비속어 삭제가 성공했습니다.");
    }

}