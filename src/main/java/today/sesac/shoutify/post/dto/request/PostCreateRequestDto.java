package today.sesac.shoutify.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
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
    private String conceptType;

    /**
     * 감정 타입
     * null일 경우 ai가 감정 분석을 해줍니다.
     */
    private String emotionType;

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

    public PostCreateRequestDto() {
    }
}
