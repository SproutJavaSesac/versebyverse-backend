package today.sesac.shoutify.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.ai.dto.request.CensorRequest;
import today.sesac.shoutify.ai.service.AiService;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/texts/censor")
    public String censorText(@RequestBody CensorRequest censorRequest) {

        log.info("@@censor request: {}", censorRequest.toString());
        return aiService.censorText(censorRequest);

//        CensorResponse censorResponse = new CensorResponse();

//        return ApiResponse.success(censorResponse);
    }

}
