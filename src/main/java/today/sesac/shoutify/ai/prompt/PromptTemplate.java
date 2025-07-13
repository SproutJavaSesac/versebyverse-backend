package today.sesac.shoutify.ai.prompt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.shoutify.ai.dto.request.AiRequestDto;

/**
 * AI 프롬프트 메시지 구성을 위한 템플릿 클래스입니다.
 * <br>역할(role), 조건(condition), 예시(example)를 포함하며, 입력 객체는 동적으로삽입됩니다.
 *
 * <ul>
 *     <li>주로 LLM 프롬프트 설계에 사용됩니다.</li>
 *     <li>프롬프트의 각 영역은 템플릿 파일에서 분리하여 읽어올 수 있습니다.</li>
 * </ul>
 *
 * <pre>
 * {
 *   "role": "...",
 *   "condition": "...",
 *   "example": "...",
 *   "input": "{ ... }",
 *   "output": {}
 * }
 * </pre>
 *
 * @see today.sesac.shoutify.ai.prompt.PromptTemplateLoader
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
     * @throws IllegalArgumentException 입력 객체의 JSON 변환에 실패한 경우
     */
    public String buildPromptMessage(AiRequestDto inputDto) {
        // TODO : 커스텀 예외 처리 적용하기
        // TODO : 예외 발생 시 처리 방법 고민하기
        // TODO : prompt .md파일 형식 변경 고민
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