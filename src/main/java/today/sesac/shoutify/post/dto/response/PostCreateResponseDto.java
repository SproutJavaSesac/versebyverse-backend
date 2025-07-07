package today.sesac.shoutify.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 게시글 작성 응답 dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostCreateResponseDto {

    private final Long postId;

    private final String afterTitle;

    private final String afterContent;
}
