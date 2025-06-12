package today.sesac.shoutify.profanity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.profanity.repository.ProfanityRepository;

/**
 * 비속어 정보를 관리하는 서비스입니다. 비속어 CRUD를 담당합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProfanityService {

    private ProfanityRepository profanityRepository;

}
