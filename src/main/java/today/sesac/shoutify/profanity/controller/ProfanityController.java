package today.sesac.shoutify.profanity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.profanity.entity.Profanity;
import today.sesac.shoutify.profanity.service.ProfanityService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfanityController {

    private final ProfanityService profanityService;

    @PostMapping("/profanities")
    public ResponseEntity<?> enroll(Profanity profanity) {

        return ResponseEntity.ok(profanity);
    }

}
