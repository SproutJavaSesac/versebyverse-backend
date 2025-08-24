package today.sesac.versebyverse.ai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.global.domain.Genre;

/**
 * 게시글 AI 응답 데이터 전송 객체(DTO)입니다.
 *
 * <p>AI 서비스에서 게시글 분석, 변환, 또는 처리 결과를 반환할 때 사용됩니다.
 * {@link AiResponseDto}를 상속받아 게시글 특화 응답 데이터를 담습니다.
 * </p>
 *
 * @see AiResponseDto
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostAiResponseDto extends AiResponseDto {

    /**
     * AI가 처리한 게시글 제목.
     */
    private String title;

    private String conceptType;

    /**
     * AI가 분석한 게시글 장르 타입.
     */
    private Genre genreType;

    /**
     * AI가 분석한 게시글 감정 타입.
     */
    private Emotion emotionType;

    /**
     * AI가 처리한 게시글 내용.
     */
    private String content;
}