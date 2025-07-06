package today.sesac.shoutify.member.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyCommentSummary {

    private Long commentId;
    private Long postId;
    private String postTitle;
    private String beforeContent;
    private String afterContent;
    private int reactionCount;
    private LocalDateTime createdAt;
}