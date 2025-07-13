package today.sesac.shoutify.ai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.shoutify.ai.dto.request.CommentAiRequestDto;

/**
 * 댓글 AI 응답 데이터 전송 객체(DTO)입니다.
 *
 * <p>AI 서비스에서 댓글 분석이나 처리 결과를 반환할 때 사용됩니다.
 * {@link AiResponseDto}를 상속받아 댓글 특화 응답 데이터를 담습니다.
 * </p>
 *
 * @see AiResponseDto
 */
@Getter
@AllArgsConstructor(staticName = "of")
class CommentAiResponseDto extends AiResponseDto<CommentAiRequestDto> {

    /**
     * AI가 처리한 댓글 내용.
     */
    private String content;
}
