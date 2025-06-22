package today.sesac.shoutify.ai.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.ai.dto.response.CensorResponse;
import today.sesac.shoutify.post.dto.request.PostRequestDto;

/**
 * AI 욕설 문장 변환, 감정 분석 요청할 떄 사용됩니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AiServiceImpl {

  private final ChatClient chatClient;

  /**
   * 테스트를 위한 임시 메서드.
   *
   * @param dto 게시글 dto
   * @return ai 응답 문자열
   */
  public String censorText(PostRequestDto dto) {
    log.info("ChatClient: {}", chatClient.getClass().getSimpleName());
    String title = dto.getTitle();
    String content = dto.getContent();

    // 더 구체적인 프롬프트 작성
    String promptText = String.format(
        "다음 텍스트에서 욕설이나 부적절한 표현을 순화시켜주세요. 원문: '%s'",
        content != null ? content : "dddd"
    );

    Prompt prompt = new Prompt(promptText);
    String result = chatClient.prompt(prompt).call().content();

    log.info("변환 결과: {}", result);
    return result;
  }

  /**
   * 욕설 변환.
   */
  public CensorResponse sanitizeProfanity(PostRequestDto dto) {

    Prompt prompt = new Prompt("");
    return null;
  }

}
