package today.sesac.versebyverse.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.ai.dto.request.CommentAiRequestDto;
import today.sesac.versebyverse.ai.dto.response.CommentAiResponseDto;
import today.sesac.versebyverse.ai.prompt.PromptTemplateLoader;

/**
 * 댓글 관련 AI 서비스입니다.
 *
 * <p>댓글의 감정 분석, 욕설 필터링 등의 AI 기능을 제공합니다.
 * 호출부에서는 PromptType과 RequestDto만 전달하면 해당 기능을 수행합니다.</p>
 */
@Service
public class CommentAiService extends AbstractAiService<CommentAiRequestDto, CommentAiResponseDto> {

    /**
     * CommentAiService 생성자.
     */
    public CommentAiService(ChatClient chatClient,
            PromptTemplateLoader promptTemplateLoader,
            ObjectMapper objectMapper) {

        super(chatClient, promptTemplateLoader, objectMapper);
    }

    /**
     * 댓글 AI 응답 DTO의 타입을 반환합니다.
     *
     * @return CommentAiResponseDto.class
     */
    @Override
    protected Class<CommentAiResponseDto> responseType() {

        return CommentAiResponseDto.class;
    }
}