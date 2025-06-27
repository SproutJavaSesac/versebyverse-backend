package today.sesac.shoutify.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.ai.dto.request.AiRequestDto;
import today.sesac.shoutify.ai.dto.response.AiResponseDto;
import today.sesac.shoutify.ai.prompt.PromptTemplate;
import today.sesac.shoutify.ai.prompt.PromptTemplateLoader;
import today.sesac.shoutify.ai.prompt.PromptType;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiDispatcherService {

    private final ChatClient chatClient;
    private final PromptTemplateLoader loader;
    private final ObjectMapper objectMapper;

    public <Req extends AiRequestDto<Resp>, Resp extends AiResponseDto> Resp process(
            Req requestDto, Class<Resp> responseType,
            PromptType promptType) {
        try {
            PromptTemplate template = loader.getTemplate(promptType);
            String prompt = template.buildPromptMessage(requestDto);
            String result = chatClient.prompt(prompt).call().content();

            result = Objects.requireNonNull(result)
                    .replaceAll("(?s)^.*?```json", "")   // ```json 이전 모두 제거
                    .replaceAll("```.*$", "")           // 마지막 ``` 이후 모두 제거
                    .trim();
            return objectMapper.readValue(result, responseType);
        } catch (JsonProcessingException e) {
            // 응답 파싱 중 I/O 오류
            throw new IllegalStateException("AI 응답 결과를 파싱하는 데 실패했습니다.", e);
        }
    }

}
