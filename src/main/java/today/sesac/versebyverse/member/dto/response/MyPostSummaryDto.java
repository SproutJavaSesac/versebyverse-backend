package today.sesac.versebyverse.member.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Genre;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 사용자가 작성한 게시글 응답 DTO.
 */
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

    private Genre genreType;

    private int reactionCount;

    private int commentCount;

    private String imageUrl;

    private Boolean isHidden;

    /**\
     * Post 객체를 사용자가 작성한 게시글 응답 DTO로 변환합니다.
     *
     * @param post 변환할 post 객체
     * @return 사용자가 작성한 게시글 응답 DTO
     */
    public static MyPostSummaryDto of(Post post, int commentCount) {

        return new MyPostSummaryDto(
                post.getId(),
                post.getBeforeTitle(),
                post.getAfterTitle(),
                post.getBeforeContent(),
                post.getAfterContent(),
                post.getCreatedAt(),
                post.getEmotionType(),
                post.getGenreType(),
                3,  // TODO: 반응하기 미구현된 관계로 하드코딩 - 삭제하기
                commentCount,
                post.getImageUrl(),
                post.isHidden()
        );
    }
}