package today.sesac.shoutify.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.ai.dto.request.PostAiRequestDto;
import today.sesac.shoutify.ai.dto.response.PostAiResponseDto;
import today.sesac.shoutify.ai.prompt.PromptTemplateLoader;

/**
 * 게시글 도메인의 AI 처리를 담당하는 서비스입니다.
 *
 * <p>컨셉에 맞는 글 변환, 감정 분석 등 게시글 관련 AI 작업을 수행합니다.</p>
 */
@Service
public class PostAiService extends AbstractAiService<PostAiRequestDto, PostAiResponseDto> {

    /**
     * PostAiService 생성자.
     */
    public PostAiService(ChatClient chatClient, PromptTemplateLoader promptTemplateLoader,
            ObjectMapper objectMapper) {
        super(chatClient, promptTemplateLoader, objectMapper);
    }

    /**
     * 게시글 AI 응답 DTO 타입 반환.
     *
     * @return PostAiResponseDto
     */
    @Override
    protected Class<PostAiResponseDto> responseType() {
        return PostAiResponseDto.class;
    }
}