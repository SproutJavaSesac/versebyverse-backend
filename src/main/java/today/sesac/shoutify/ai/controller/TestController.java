package today.sesac.shoutify.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.ai.service.AiService;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class TestController {

    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;
    private final AiService aiService;

    @PostMapping("/chat")
    public ResponseEntity<String> chatWithGemini(@RequestBody String input) {
        String reply = aiService.askGemini(input);
        return ResponseEntity.ok(reply);
    }


}
