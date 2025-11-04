package today.sesac.versebyverse.profanity.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.annotation.Validated;
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
import today.sesac.versebyverse.profanity.dto.request.ProfanityRegisterRequestDto;
import today.sesac.versebyverse.profanity.dto.request.ProfanityUpdateRequestDto;
import today.sesac.versebyverse.profanity.dto.response.ProfanityListResponseWrapperDto;
import today.sesac.versebyverse.profanity.dto.response.ProfanityResponseDto;
import today.sesac.versebyverse.profanity.service.ProfanityService;

/**
 * 비속어 관련 API 컨트롤러.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/profanities")
@RequiredArgsConstructor
public class ProfanityController {

    private final ProfanityService profanityService;

    /**
     * 비속어 목록 조회 API입니다.
     *
     * @param page  현재 페이지 기본 0
     * @param size  한 페이지에 나타낼 데이터 수 기본20/ 50/ 100
     * @param sort  정렬 필드 (id, original, createdAt, updatedAt)
     * @param order 정렬 순서 (latest, oldest)
     * @return 응답
     */
    @GetMapping
    public ApiResponse<ProfanityListResponseWrapperDto> getProfanityList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(name = "order", defaultValue = "DESC") Direction order) {

        ProfanityListResponseWrapperDto profanityPagingList =
                profanityService.getProfanityList(page, size, sort, order);

        return ApiResponse.success(profanityPagingList);
    }

    /**
     * 비속어 등록 API.
     *
     * @param profanityRegisterRequestDto 클라이언트 입력 비속어등록 Dto
     * @return 응답
     */
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")  //Spring Security 권한 제어 어노테이션 TODO: 회원 권한 완료되면 주석풀기
    public ApiResponse<ProfanityResponseDto> registerProfanity(
            @Valid @RequestBody ProfanityRegisterRequestDto profanityRegisterRequestDto) {

        return ApiResponse.success(profanityService.registerProfanity(profanityRegisterRequestDto));
    }

    /**
     * 비속어 수정 API. 비속어 원문, 대체어, 설명, 카테고리를 수정 할 수 있습니다.
     *
     * @param profanityId 비속어 식별 id
     * @return 응답
     */
    @PatchMapping("/{profanityId}")
    public ApiResponse<ProfanityResponseDto> updateProfanity(@PathVariable long profanityId,
            @Valid @RequestBody
            ProfanityUpdateRequestDto profanityUpdateRequestDto) {

        return ApiResponse.success(
                profanityService.updateProfanity(profanityId, profanityUpdateRequestDto));
    }

    /**
     * 비속어 삭제 API.
     *
     * @param profanityId 비속어 식별 id
     * @return 응답
     */
    @DeleteMapping("/{profanityId}")
    public ApiResponse<String> deleteProfanity(@PathVariable long profanityId) {

        return ApiResponse.success(profanityService.deleteProfanity(profanityId));
    }

    /**
     * 비속어 키워드 검색 API.
     * 비속어 원문(original), 대체어(replacement), 설명(description) 필드에서
     * 대소문자 구분 없이 키워드를 포함하는 비속어 목록을 검색합니다.
     *
     * @param keyword 검색할 키워드 (비속어 원문, 대체어, 설명에서 검색)
     * @param page    현재 페이지 (기본값: 0)
     * @param size    한 페이지에 나타낼 데이터 수 (기본값: 20)
     * @return 키워드가 포함된 비속어 목록과 페이지네이션 정보를 담은 응답
     */
    @GetMapping("/search")
    public ApiResponse<ProfanityListResponseWrapperDto> searchProfanities(
            @RequestParam @NotBlank String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){

        Pageable pageable = PageRequest.of(page, size,  Sort.by(Direction.DESC, "createdAt"));
        ProfanityListResponseWrapperDto profanityPagingList = profanityService.searchProfanities(keyword, pageable);
        return ApiResponse.success(profanityPagingList);
    }

}