package today.sesac.shoutify.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.ai.service.AiServiceImpl;
import today.sesac.shoutify.post.dto.request.PostRequestDto;


@Slf4j
@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class TestController {

  private final VertexAiGeminiChatModel vertexAiGeminiChatModel;
  private final AiServiceImpl aiService;

  @PostMapping("/chat")
  public ResponseEntity<String> chatWithGemini(@RequestBody PostRequestDto dto) {
    log.info("@@@@@@post : {}", dto);
    String reply = aiService.censorText(dto);
    return ResponseEntity.ok(reply);
  }


}
