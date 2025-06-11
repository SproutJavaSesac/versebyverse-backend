package today.sesac.shoutify.profanity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.profanity.repository.ProfanityRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfanityService {

    private ProfanityRepository profanityRepository;

}
