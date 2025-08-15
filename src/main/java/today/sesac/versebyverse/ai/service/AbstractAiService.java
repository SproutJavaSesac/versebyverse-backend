package today.sesac.versebyverse.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import today.sesac.versebyverse.ai.dto.request.AiRequestDto;
import today.sesac.versebyverse.ai.dto.response.AiResponseDto;
import today.sesac.versebyverse.ai.prompt.PromptTemplate;
import today.sesac.versebyverse.ai.prompt.PromptTemplateLoader;
import today.sesac.versebyverse.ai.prompt.PromptType;

/**
 * 도메인별 AI 서비스의 공통 로직을 담당하는 추상 클래스.
 *
 * <p>모든 도메인별 AI 서비스는 이 클래스를 상속받아 구현해야 합니다.
 * AI 요청 처리의 공통 로직(프롬프트 생성, AI 호출, 응답 파싱)을 제공합니다.</p>
 *
 * @param <T> AI 요청 DTO 타입.
 * @param <R> AI 응답 DTO 타입.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractAiService<T extends AiRequestDto, R extends AiResponseDto> {

    private final ChatClient chatClient;

    private final PromptTemplateLoader promptTemplateLoader;

    private final ObjectMapper objectMapper;

    /**
     * 프롬프트 타입에 따라 AI를 호출하고, 결과를 파싱하여 DTO로 반환합니다.
     *
     * @param requestDto 호출에 사용할 입력 데이터
     * @param promptType 사용할 프롬프트 타입
     * @return AI 응답 결과 DTO
     * @throws IllegalStateException 응답 파싱 실패 시 발생
     */
    public R executeAi(T requestDto, PromptType promptType) {

        PromptTemplate template = promptTemplateLoader.getTemplate(promptType);
        String prompt = template.buildPromptMessage(requestDto);
        String response = chatClient.prompt(prompt).call().content();
        return parseResponseMessageToDto(response);
    }

    /**
     * AI 응답메세지(JSON)을 응답 DTO 객체로 파싱하는 메서드.
     *
     * @param responseMessage AI 응답 메세지
     * @return 파싱된 응답 DTO 객체
     * @throws IllegalStateException 응답 파싱 실패 시 발생
     */
    private R parseResponseMessageToDto(String responseMessage) {

        try {
            String content = cleanJsonBlock(responseMessage);
            return objectMapper.readValue(content, responseType());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("[AbstractAiService] AI 응답 파싱 실패 : ", e);
        }
    }

    private String cleanJsonBlock(String raw) {

        return raw.replaceAll("(?s)^.*?```json", "")
                .replaceAll("```.*$", "")
                .trim();
    }

    /**
     * 자식 클래스가 응답 DTO 타입을 명시적으로 반환해야 합니다.
     *
     * @return 응답 DTO 클래스 타입
     */
    protected abstract Class<R> responseType();
}