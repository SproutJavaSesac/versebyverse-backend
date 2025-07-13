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

/**
 * AI 요청 타입을 처리하고, 프롬프트 템플릿을 통해 ChatClient로 요청을 전송한 후 응답을 파싱하여 반환하는 서비스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AiDispatcherService {

    private final ChatClient chatClient;

    private final PromptTemplateLoader loader;

    private final ObjectMapper objectMapper;

    /**
     * AI 요청을 처리하고 지정된 Dto로 응답을 반환합니다.
     *
     * @param <T>          응답 DTO 타입, AiResponseDto를 상속해야 함.
     * @param requestDto   AI 요청 데이터를 담은 DTO 객체
     * @param responseType 응답으로 받을 클래스 타입
     * @param promptType   사용할 프롬프트 템플릿 타입
     */
    public <T extends AiResponseDto<?>> T process(
            AiRequestDto requestDto, Class<T> responseType,
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
