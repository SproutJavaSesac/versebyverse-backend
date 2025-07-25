package today.sesac.versebyverse.profanity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.profanity.dto.request.ProfanityRegisterRequestDto;
import today.sesac.versebyverse.profanity.dto.request.ProfanityUpdateRequestDto;
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
     * 비속어를 등록합니다.
     *
     * <p>전달받은 원문 비속어가 이미 등록되어 있는 경우 {@link ProfanityException} 예외를 발생시키며,
     * 존재하지 않을 경우 새 비속어 엔티티를 생성하고 저장합니다.
     * 저장된 비속어 정보를 응답 DTO로 반환합니다.
     * </p>
     *
     * @param profanityRegisterRequestDto 등록할 비속어 정보가 담긴 DTO (원문, 대체어, 설명, 카테고리)
     * @return 저장된 비속어 정보를 담은 응답 DTO
     * @throws ProfanityException 이미 동일한 원문 비속어가 존재하는 경우 발생
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
     * 주어진 profanityId에 해당하는 {@link Profanity} 엔티티를 조회한 뒤,
     * 전달받은 {@link ProfanityUpdateRequestDto}의 값으로 엔티티를 부분 업데이트합니다.
     *
     * <p>존재하지 않는 ID일 경우 {@link ProfanityException}을 발생시킵니다.
     * 변경 가능한 필드는 original, replacement, description, category입니다.
     * null 또는 빈 문자열("") 값은 무시되며 기존 값이 유지됩니다.</p>
     *
     * @param profanityId 수정할 대상의 비속어 ID
     * @param profanityUpdateRequestDto         수정할 필드 값을 담은 요청 DTO
     * @throws ProfanityException 비속어가 존재하지 않을 경우
     */
    @Transactional
    public ProfanityResponseDto updateProfanity(long profanityId,
            ProfanityUpdateRequestDto profanityUpdateRequestDto) {
        Profanity profanity = profanityRepository.findById(profanityId)
                .orElseThrow(() -> new ProfanityException(ProfanityErrorCode.PROFANITY_NOT_FOUND,
                        "ProfanityID: " + profanityId));

        // original 값이 변경되는 경우에만 중복 체크
        if (profanityUpdateRequestDto.getOriginal() != null
                && !profanityUpdateRequestDto.getOriginal().equals(profanity.getOriginal())
                && profanityRepository.existsByOriginal(profanityUpdateRequestDto.getOriginal())) {
            log.error("비속어 업데이트 실패 - 이미 존재함: {}", profanityUpdateRequestDto.getOriginal());
            throw new ProfanityException(ProfanityErrorCode.PROFANITY_ALREADY_EXISTS,
                    profanityUpdateRequestDto.getOriginal());
        }

        // 변경사항이 없을 경우 예외 발생
        if (hasNoChanges(profanityUpdateRequestDto, profanity)) {
            log.warn("비속어 업데이트 실패 - 변경사항 없음: id={}", profanityId);
            throw new ProfanityException(ProfanityErrorCode.PROFANITY_NO_CHANGES, String.valueOf(profanityId));
        }

        profanity.updateProfanity(profanityUpdateRequestDto);
        return ProfanityResponseDto.of(profanity.getId(), profanity.getOriginal(), profanity.getReplacement(),
                profanity.getDescription(), profanity.getCategory());
    }

    /**
     * 변경사항이 없는지 확인(dto와 profanity 필드 값 일치).
     */
    private boolean hasNoChanges(ProfanityUpdateRequestDto dto, Profanity profanity) {
        boolean originalUnchanged = dto.getOriginal() == null
                || dto.getOriginal().isEmpty()
                || dto.getOriginal().equals(profanity.getOriginal());

        boolean replacementUnchanged = dto.getReplacement() == null
                || dto.getReplacement().isEmpty()
                || dto.getReplacement().equals(profanity.getReplacement());

        boolean descriptionUnchanged = dto.getDescription() == null
                || dto.getDescription().isEmpty()
                || dto.getDescription().equals(profanity.getDescription());

        boolean categoryUnchanged = dto.getCategory() == null
                || dto.getCategory().equals(profanity.getCategory());

        return originalUnchanged && replacementUnchanged && descriptionUnchanged && categoryUnchanged;
    }
}