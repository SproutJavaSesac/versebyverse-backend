package today.sesac.shoutify.ai.prompt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.shoutify.ai.dto.request.AiRequestDto;

/**
 * AI 프롬프트 메시지 구성을 위한 템플릿 클래스입니다. 역할(role), 조건(condition), 예시(example)를 포함하며, 입력 객체는 동적으로 삽입됩니다.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PromptTemplate {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String role;
    private final String condition;
    private final String example;

    /**
     * 입력 객체를 JSON으로 변환하여 프롬프트 메시지를 생성합니다.
     *
     * @param inputDto 입력 DTO 객체 (예: PostRequestDto, CommentRequestDto 등)
     * @return 전체 프롬프트 문자열
     * @throws IllegalArgumentException JSON 변환 실패 시
     */
    public String buildPromptMessage(AiRequestDto inputDto) {
        try {
            String inputJson = objectMapper.writeValueAsString(inputDto);
            return """
                    {
                      "role": "%s",
                      "condition": "%s",
                      "example": "%s",
                      "input": "%s",
                      "output": {}
                    }
                    """.formatted(role, condition, example, inputJson);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("프롬프트 입력 객체를 JSON으로 변환하는 데 실패했습니다.", e);
        }
    }

}