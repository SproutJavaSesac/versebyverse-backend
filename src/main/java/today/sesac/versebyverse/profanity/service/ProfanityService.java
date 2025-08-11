package today.sesac.versebyverse.profanity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.profanity.dto.request.ProfanityRegisterRequestDto;
import today.sesac.versebyverse.profanity.dto.request.ProfanityUpdateRequestDto;
import today.sesac.versebyverse.profanity.dto.response.ProfanityListResponseWrapperDto;
import today.sesac.versebyverse.profanity.dto.response.ProfanityResponseDto;
import today.sesac.versebyverse.profanity.entity.Profanity;
import today.sesac.versebyverse.profanity.exception.ProfanityErrorCode;
import today.sesac.versebyverse.profanity.exception.ProfanityException;
import today.sesac.versebyverse.profanity.repository.ProfanityRepository;

/**
 * 비속어 정보를 관리하는 서비스입니다. 비속어 정보의 추가, 조회, 수정, 삭제 기능을 제공합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProfanityService {

    private final ProfanityRepository profanityRepository;

    /**
     * 비속어 등록.
     *
     * @param profanityRegisterRequestDto 클라이언트에서 입력 받은 비속어 정보 DTO
     * @return 등록되 비속어 정보 DTO
     * @throws ProfanityException original 값이 이미 존재할 경우 발생
     */
    @Transactional
    public ProfanityResponseDto registerProfanity(
            ProfanityRegisterRequestDto profanityRegisterRequestDto) {

        if (profanityRepository.existsByOriginal(profanityRegisterRequestDto.original())) {
            throw new ProfanityException(ProfanityErrorCode.PROFANITY_ALREADY_EXISTS,
                    "original: " + profanityRegisterRequestDto.original());
        }
        Profanity profanity = Profanity.create(
                profanityRegisterRequestDto.original(),
                profanityRegisterRequestDto.replacement(),
                profanityRegisterRequestDto.description(),
                profanityRegisterRequestDto.category()
        );
        Profanity saved = profanityRepository.save(profanity);
        return ProfanityResponseDto.of(saved.getId(), saved.getOriginal(), saved.getReplacement(),
                saved.getDescription(), saved.getCategory());
    }

    /**
     * 비속어 삭제(hard-delete).
     *
     * @param profanityId 클라인언트에서 요청한 비속어 ID
     * @return 성공 메세지
     * @throws ProfanityException 해당 비속어 id의 데이터가 없을 경우 발생
     */
    @Transactional
    public String deleteProfanity(long profanityId) {

        if (profanityRepository.deleteByIdIfExists(profanityId) != 1) {
            throw new ProfanityException(ProfanityErrorCode.PROFANITY_NOT_FOUND,
                    String.valueOf(profanityId));
        } else {
            return "비속어 삭제가 성공했습니다.";
        }
    }

    /**
     * 주어진 profanityId에 해당하는 {@link Profanity} 엔티티를 조회한 뒤, 전달받은 {@link ProfanityUpdateRequestDto}의
     * 값으로 엔티티를 부분 업데이트합니다.
     *
     * <p>존재하지 않는 ID일 경우 {@link ProfanityException}을 발생시킵니다.
     * 변경 가능한 필드는 original, replacement, description, category입니다. null 또는 빈 문자열("") 값은 무시되며 기존 값이
     * 유지됩니다.</p>
     *
     * @param profanityId               수정할 대상의 비속어 ID
     * @param profanityUpdateRequestDto 수정할 필드 값을 담은 요청 DTO
     * @throws ProfanityException 비속어가 존재하지 않을 경우
     */
    @Transactional
    public ProfanityResponseDto updateProfanity(long profanityId,
            ProfanityUpdateRequestDto profanityUpdateRequestDto) {

        Profanity profanity = profanityRepository.findById(profanityId)
                .orElseThrow(() -> new ProfanityException(ProfanityErrorCode.PROFANITY_NOT_FOUND,
                        "ProfanityID: " + profanityId));

        // original 값이 변경되는 경우에만 중복 체크
        if (!profanityUpdateRequestDto.getOriginal().equals(profanity.getOriginal())
                && profanityRepository.existsByOriginal(profanityUpdateRequestDto.getOriginal())) {
            log.warn("비속어 수정 실패 - 이미 존재하는 original: {}", profanityUpdateRequestDto.getOriginal());
            throw new ProfanityException(ProfanityErrorCode.PROFANITY_ALREADY_EXISTS,
                    profanityUpdateRequestDto.getOriginal());
        }

        profanity.updateProfanity(profanityUpdateRequestDto);

        return ProfanityResponseDto.of(profanity.getId(), profanity.getOriginal(),
                profanity.getReplacement(),
                profanity.getDescription(), profanity.getCategory());
    }

    /**
     * 비속어 목록을 페이지네이션과 함께 조회합니다.
     *
     * @param page  현재 페이지 (0부터 시작)
     * @param size  페이지 크기
     * @param sort  정렬 필드
     * @param order 정렬 순서 (ASC, DESC)
     * @return 비속어 목록과 페이지네이션 정보
     */
    public ProfanityListResponseWrapperDto getProfanityList(int page, int size, String sort,
            String order) {

        if (!isValidSort(sort)) {
            sort = "createdAt";
        }
        Direction direction = switch (order.toLowerCase()) {
            case "latest" -> Direction.DESC;
            case "oldest" -> Direction.ASC;
            default -> Direction.DESC; // 기본값은 최신순
        };
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(direction, sort));

        Page<Profanity> profanityPage = profanityRepository.findAll(pageable);

        return ProfanityListResponseWrapperDto.of(profanityPage);
    }

    private boolean isValidSort(String sort) {

        return sort != null && (sort.equals("createdAt") || sort.equals("updatedAt") || sort.equals(
                "original") || sort.equals("id"));
    }
}