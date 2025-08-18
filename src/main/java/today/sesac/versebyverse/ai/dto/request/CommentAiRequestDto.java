package today.sesac.versebyverse.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 댓글 AI 요청 데이터 전송 객체(DTO)입니다.
 *
 * <p>댓글 내용을 AI 서비스에 전달하여 분석이나 처리를 요청할 때 사용됩니다. {@link AiRequestDto}를 상속받아 댓글 특화 요청 데이터를 담습니다.
 * </p>
 *
 * @see AiRequestDto
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class CommentAiRequestDto extends AiRequestDto {

    private Concept conceptType;

    /**
     * 분석할 댓글 내용.
     */
    private String content;

    /**
     * 댓글 감정.
     */
    private Emotion emotion;
}