package today.sesac.shoutify.profanity.controller;

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
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.profanity.dto.request.ProfanityRegisterRequestDto;
import today.sesac.shoutify.profanity.dto.request.ProfanityUpdateRequestDto;
import today.sesac.shoutify.profanity.dto.response.ProfanityListResponseWrapperDto;
import today.sesac.shoutify.profanity.dto.response.ProfanityResponseDto;
import today.sesac.shoutify.profanity.service.ProfanityService;
import today.sesac.shoutify.temp.PaginationDto;

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
    public ApiResponse<?> profanity(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "sort", defaultValue = "latest") String sort) {
        List<ProfanityResponseDto> profanities = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            int id = ((page - 1) * size) + i;
            profanities.add(
                    ProfanityResponseDto.builder()
                            .profanityId(id)
                            .original("비속어" + id)
                            .replacement("순화어" + id)
                            .description("설명" + id)
                            .category("GENERAL_SWEAR")
                            .build()
            );
        }

        PaginationDto pagination = PaginationDto.builder()
                .currentPage(page)
                .pageSize(size)
                .totalCount(1000)
                .totalPage((int) Math.ceil(1000.0 / size))
                .hasNext(page * size < 1000)
                .hasPrev(page > 1)
                .build();

        ProfanityListResponseWrapperDto response = ProfanityListResponseWrapperDto.builder()
                .profanities(profanities)
                .pagination(pagination)
                .sort(sort)
                .build();

        return ApiResponse.success(response);
    }

    /**
     * 비속어 등록 API.
     *
     * @param dto 클라이언트 입력 비속어등록 Dto
     * @return 응답
     */
    @PostMapping
    public ApiResponse<?> registerProfanity(
            @RequestBody ProfanityRegisterRequestDto dto) {

        return ApiResponse.success(
                ProfanityResponseDto.builder()
                        .profanityId((int) (Math.random() * 100) + 1)
                        .original(dto.getOriginal())
                        .replacement(dto.getReplacement())
                        .description(dto.getDescription())
                        .category(dto.getCategory())
                        .build()
        );
    }

    /**
     * 비속어 수정 API. 비속어 원문, 대체어, 설명, 카테고리를 수정 할 수 있습니다.
     *
     * @param profanityId 비속어 식별 id
     * @return 응답
     */
    @PatchMapping("{profanityId}")
    public ApiResponse<?> updateProfanity(@PathVariable int profanityId,
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
    public ApiResponse<?> deleteProfanity(@PathVariable int profanityId) {

        return ApiResponse.success("비속어 삭제가 성공했습니다.");
    }


}
