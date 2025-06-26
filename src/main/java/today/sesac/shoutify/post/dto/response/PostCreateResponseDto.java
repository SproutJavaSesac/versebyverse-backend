package today.sesac.shoutify.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostCreateResponseDto {
    private final Long postId;
    private final String title;
    private final String afterContent;
}
