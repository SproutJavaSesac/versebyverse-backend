package today.sesac.shoutify.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class PostCreateRequestDto {
    @NotBlank
    private String title;
    @NotNull
    private String conceptType;
    private String emotionType;
    @NotBlank
    @Length(max = 1000)
    private String content;
    private String imageUrl;

    public PostCreateRequestDto() {
    }
}
