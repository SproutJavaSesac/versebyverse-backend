package today.sesac.shoutify.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.ai.service.AiService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;


}
