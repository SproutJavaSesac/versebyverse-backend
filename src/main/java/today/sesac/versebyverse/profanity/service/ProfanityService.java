package today.sesac.versebyverse.profanity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.profanity.dto.request.ProfanityRegisterRequestDto;
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
}