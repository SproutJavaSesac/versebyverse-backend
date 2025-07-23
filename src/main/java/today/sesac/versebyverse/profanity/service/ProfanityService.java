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

    @Transactional
    public ProfanityResponseDto registerProfanity(ProfanityRegisterRequestDto dto) {

        if (profanityRepository.existsByOriginal(dto.original())) {
            throw new ProfanityException(ProfanityErrorCode.PROFANITY_ALREADY_EXISTS,
                    "original: " + dto.original());
        }
        Profanity profanity = Profanity.create(
                dto.original(),
                dto.replacement(),
                dto.description(),
                dto.category()
        );
        Profanity saved = profanityRepository.save(profanity);
        return ProfanityResponseDto.of(saved.getId(), saved.getOriginal(), saved.getReplacement(),
                saved.getDescription(), saved.getCategory());
    }
}