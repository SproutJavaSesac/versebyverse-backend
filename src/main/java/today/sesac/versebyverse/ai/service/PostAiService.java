package today.sesac.versebyverse.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.ai.dto.request.PostAiRequestDto;
import today.sesac.versebyverse.ai.dto.response.PostAiResponseDto;
import today.sesac.versebyverse.ai.prompt.PromptTemplateLoader;
import today.sesac.versebyverse.ai.prompt.PromptType;

/**
 * 게시글 도메인의 AI 처리를 담당하는 서비스입니다.
 *
 * <p>컨셉에 맞는 글 변환, 감정 분석 등 게시글 관련 AI 작업을 수행합니다.</p>
 */
@Service
@Slf4j
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

    /**
     * AI 요청을 실행하고 응답을 검증하여 안전한 결과를 반환합니다.
     *
     * <p>AI 처리 실패 시 또는 필수 필드가 누락된 경우 원본 데이터로 대체하여
     * 서비스 안정성을 보장합니다.</p>
     *
     * @param requestDto AI 요청 데이터 전송 객체
     * @param promptType 사용할 프롬프트 타입
     * @return 검증된 AI 응답 DTO 또는 대체 응답 DTO
     */
    public PostAiResponseDto executeAiWithValidation(PostAiRequestDto requestDto,
            PromptType promptType) {

        try {
            PostAiResponseDto response = executeAi(requestDto, promptType);

            // 필수 필드 검증
            if (response.getTitle() == null || response.getContent() == null) {
                log.warn("AI 응답 필수 필드 누락, 원본 데이터로 대체");
                return createFallbackResponse(requestDto);
            }

            return response;
        } catch (Exception e) { //TODO: 커스텀예외 처리하기.
            log.warn("AI 처리 실패, 원본 데이터로 대체: ", e);
            return createFallbackResponse(requestDto);
        }
    }

    /**
     * AI 문장 변환 실패 시 원본 데이터로 대체 응답을 생성합니다.
     *
     * @param requestDto 원본 요청 데이터
     * @return 원본 데이터를 기반으로 한 대체 응답 DTO
     */
    private PostAiResponseDto createFallbackResponse(PostAiRequestDto requestDto) {

        return PostAiResponseDto.of(
                requestDto.getTitle(),
                requestDto.getConceptType(),
                requestDto.getGenreType(),
                requestDto.getEmotionType(),
                requestDto.getContent()
        );
    }
}