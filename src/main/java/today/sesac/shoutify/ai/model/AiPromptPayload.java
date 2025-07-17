package today.sesac.shoutify.ai.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.shoutify.ai.dto.request.AiRequestDto;

/**
 * AI 프롬프트 메시지 생성을 위한 데이터 페이로드 클래스.
 *
 * <p>이 클래스는 프롬프트 메시지를 JSON 형태로 직렬화하기 위해 사용됩니다. </p>
 */

@Getter
@AllArgsConstructor(staticName = "of")
public class AiPromptPayload {

    //    private final Map<String, String> responseMessage = new HashMap<>();
    private String role;

    private String condition;

    private String example;

    private AiRequestDto aiRequestDto;
}