package today.sesac.shoutify.post.dto.request;

import lombok.Getter;

@Getter
public class PostCreateRequestDto {
    private String title;
    private String conceptType;
    private String emotionType;
    private String content;
    private String imageUrl;

    public PostCreateRequestDto() {
    }
}
