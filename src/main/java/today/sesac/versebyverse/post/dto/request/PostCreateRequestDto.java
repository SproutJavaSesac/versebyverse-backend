package today.sesac.versebyverse.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.global.domain.Genre;

/**
 * 게시글 작성 요청 dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostCreateRequestDto {

    /**
     * 변환 전 사용자가 작성하는 게시물 제목입니다.
     */
    @NotBlank
    private String title;

    /**
     * 게시물의 장르타입입니다.
     */
    @NotNull
    private Genre genreType;

    /**
     * 감정 타입 null일 경우 ai가 감정 분석을 해줍니다.
     */
    private Emotion emotionType;

    /**
     * 변환 전 사용자가 쓴 게시물 내용입니다.
     */
    @NotBlank
    @Length(max = 1000)
    private String content;

    /**
     * 이미지 URL입니다.
     */
    private String imageUrl;
}