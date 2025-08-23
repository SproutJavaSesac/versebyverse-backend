package today.sesac.versebyverse.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.ai.dto.request.CommentAiRequestDto;
import today.sesac.versebyverse.ai.dto.response.CommentAiResponseDto;
import today.sesac.versebyverse.ai.exception.AiErrorCode;
import today.sesac.versebyverse.ai.exception.AiException;
import today.sesac.versebyverse.ai.prompt.PromptTemplateLoader;
import today.sesac.versebyverse.ai.prompt.PromptType;

/**
 * 댓글 관련 AI 서비스입니다.
 *
 * <p>댓글의 감정 분석, 욕설 필터링 등의 AI 기능을 제공합니다.
 * 호출부에서는 PromptType과 RequestDto만 전달하면 해당 기능을 수행합니다.</p>
 */
@Service
@Slf4j
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

    /**
     * AI 요청을 실행하고 응답을 검증하여 안전한 결과를 반환합니다.
     *
     * @param requestDto AI 요청 데이터 전송 객체
     * @param promptType 사용할 프롬프트 타입
     * @return responseDto AI 응답 DTO
     * @throws AiException 응답의 필수 필드(conceptType, content)가 null인 경우
     */
    public CommentAiResponseDto executeAiWithValidation(CommentAiRequestDto requestDto,
            PromptType promptType) {

        CommentAiResponseDto responseDto = executeAi(requestDto, promptType);
        if (responseDto.getGenreType() == null || responseDto.getContent() == null) {
            log.warn("AI 응답 필수 필드 누락");
            throw new AiException(AiErrorCode.REQUIRED_FIELD_MISSING,
                    (responseDto.getGenreType() == null)
                            ? "conceptType"
                            : "content");
        }
        return responseDto;

    }

    /**
     * AI 문장 변환 실패 시 원본 데이터로 대체 응답을 생성합니다.
     *
     * @param requestDto 원본 요청 데이터
     * @return 원본 데이터를 기반으로 한 대체 응답 DTO
     */
    private CommentAiResponseDto createFallbackResponse(CommentAiRequestDto requestDto) {

        return CommentAiResponseDto.of(requestDto.getGenreType(), requestDto.getContent(),
                requestDto.getEmotion());
    }
}