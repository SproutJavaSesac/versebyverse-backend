package today.sesac.versebyverse.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.global.domain.Genre;

/**
 * 게시글 AI 요청 데이터 전송 객체(DTO)입니다.
 *
 * <p>게시글 정보를 AI 서비스에 전달하여 분석, 변환, 또는 처리를 요청할 때 사용됩니다.
 * {@link AiRequestDto}를 상속받아 게시글 특화 요청 데이터를 담습니다.
 * </p>
 *
 * @see AiRequestDto
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostAiRequestDto extends AiRequestDto {

    /**
     * 게시글 제목.
     */
    private String title;

    private String conceptType;

    /**
     * 게시글 장르 타입.
     */
    private Genre genreType;

    /**
     * 게시글 감정 타입.
     */
    private Emotion emotionType;

    /**
     * 게시글 내용.
     */
    private String content;
}