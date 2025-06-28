package today.sesac.shoutify.post.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class PostCreateResponseDto {

    @NotNull
    private final Long postId;

    @NotNull
    private final String afterTitle;

    @NotNull
    private final String afterContent;
}
