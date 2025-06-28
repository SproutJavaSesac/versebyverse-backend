package today.sesac.shoutify.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.global.domain.Emotion;

@Getter
@AllArgsConstructor(staticName = "of")
public class PostCreateRequestDto {

    /**
     * 게시물 제목
     */
    @NotBlank
    private String title;

    /**
     * 컨셉 타입
     */
    @NotNull
    private Concept conceptType;

    /**
     * 감정 타입 null일 경우 ai가 감정 분석을 해줍니다.
     */
    private Emotion emotionType;

    /**
     * 변환 전 사용자가 쓴 게시물 내용
     */
    @NotBlank
    @Length(max = 1000)
    private String content;

    /**
     * 이미지 URL
     */
    private String imageUrl;
}
