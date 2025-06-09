package today.sesac.shoutify.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.ai.service.AiService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AiController {

    private final AiService aiService;

    @PostMapping("/text/censor")
    public String censor() {

        return null;
    }

}
