package today.sesac.versebyverse.member.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.post.entity.Post;

@Getter
@AllArgsConstructor
public class MyPostSummaryDto {

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

    public static MyPostSummaryDto of(Post post) {

        return new MyPostSummaryDto(
                post.getId(),
                post.getBeforeTitle(),
                post.getAfterTitle(),
                post.getBeforeContent(),
                post.getAfterContent(),
                post.getCreatedAt(),
                post.getEmotionType(),
                post.getConceptType(),
                3,  // TODO: 프론트 테스트 - 리액션 미구현된 관계로 리액션 수 하드코딩
                5,  // TODO: 프론트 테스트 - 댓글 개수 하드코딩
                post.getImageUrl(),
                post.isHidden()
        );
    }
}