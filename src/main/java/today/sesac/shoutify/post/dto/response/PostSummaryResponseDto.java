package today.sesac.shoutify.post.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSummaryResponseDto {

    private Long postId;
    private String nickname;
    private String afterTitle;
    private String afterContent;
    private LocalDateTime createdAt;
    private int reactionCount;
    private int commentCount;
    private String conceptType;
    private String imageUrl;
}
