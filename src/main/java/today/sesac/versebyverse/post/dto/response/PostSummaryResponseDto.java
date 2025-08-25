package today.sesac.versebyverse.post.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 목록 조회 시 하나의 게시글을 나타내기 위한 record.
 */
//image 값이 null인 경우도 있어서 image는 final 제외 -> 빌더쓰면 안 붙여도 됨

public record PostSummaryResponseDto(

        Long authorId,

        String nickname,

        String afterTitle,

        String afterContent,

        LocalDateTime createdAt,

        int reactionCount,

        Long commentCount,

        Concept conceptType,

        Long postId,

        String imageUrl

) {

    public static PostSummaryResponseDto of(Post post, Long commentCount, int reactionCount) {

        return new PostSummaryResponseDto(
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getAfterTitle(),
                post.getAfterContent(),
                post.getCreatedAt(),
                reactionCount,
                commentCount,
                post.getConceptType(),
                post.getId(),
                post.getImageUrl()
        );
    }
}
