package today.sesac.versebyverse.profanity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.profanity.repository.ProfanityRepository;

/**
 * 비속어 정보를 관리하는 서비스입니다. 비속어 정보의 추가, 조회, 수정, 삭제 기능을 제공합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProfanityService {

    /**
     * 데이터베이스와 상호작용을 위해 ProfanityRepository를 의존성 주입받습니다.
     */
    private final ProfanityRepository profanityRepository;

}