package today.sesac.versebyverse.member.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 사용자가 작성한 게시글 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MemberPostSummaryDto {

    private Long postId;

    private String afterTitle;

    private String afterContent;

    private LocalDateTime createdAt;

    private Emotion emotionType;

    private Concept conceptType;

    private int reactionCount;

    private int commentCount;

    private String imageUrl;

    /**
     * Post 객체를 회원이 작성한 게시글 응답 DTO로 변환합니다.
     *
     * @param post 변환할 post 객체
     * @return 사용자가 작성한 게시글 응답 DTO
     */
    public static MemberPostSummaryDto of(Post post, int commentCount) {

        return new MemberPostSummaryDto(
                post.getId(),
                post.getAfterTitle(),
                post.getAfterContent(),
                post.getCreatedAt(),
                post.getEmotionType(),
                post.getConceptType(),
                3,  // TODO: 반응하기 미구현된 관계로 하드코딩 - 삭제하기
                commentCount,
                post.getImageUrl()
        );
    }

}
