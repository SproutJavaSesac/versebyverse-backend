package today.sesac.shoutify.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.ai.dto.request.AiRequestDto;
import today.sesac.shoutify.ai.dto.request.PostAiRequestDto;
import today.sesac.shoutify.ai.dto.response.AiResponseDto;
import today.sesac.shoutify.ai.dto.response.PostAiResponseDto;
import today.sesac.shoutify.ai.prompt.PromptType;
import today.sesac.shoutify.ai.service.AiDispatcherService;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.post.dto.request.PostRequestDto;


// TODO : ai 서비스 사용 예시로 잠시 남겨두겠습니다. 참고해주세요
@Slf4j
@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class TestController {

    private final AiDispatcherService dispatcherService;

    @PostMapping("/chat")
    public ApiResponse<AiResponseDto> chatWithGemini(@RequestBody PostRequestDto dto) {
        log.info("@@@@@@post : {}", dto);

        AiRequestDto requestDto = PostAiRequestDto.of(dto.getTitle(),
                dto.getConceptType(),
                dto.getEmotionType(), dto.getContent());

        AiResponseDto refined = dispatcherService.process(
                requestDto,
                PostAiResponseDto.class,
                PromptType.PROFANITY_REFINE
        );
        return ApiResponse.success(refined);
    }


}
