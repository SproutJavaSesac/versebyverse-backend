package today.sesac.shoutify.member.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.global.domain.Emotion;

@Getter
@Setter
@NoArgsConstructor
public class MyPostSummary {

    private Long postId;
    private String beforeTitle;
    private String afterTitle;
    private String beforeContent;
    private String afterContent;
    private LocalDateTime createdAt;
    private Emotion emotionType;
    private Concept conceptType;
    private int reactionCount;
    private int commentCount;
    private String imageUrl;
    private Boolean isHidden;
}
