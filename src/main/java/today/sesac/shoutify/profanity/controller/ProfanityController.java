package today.sesac.shoutify.profanity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.profanity.service.ProfanityService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfanityController {

    private final ProfanityService profanityService;


}
