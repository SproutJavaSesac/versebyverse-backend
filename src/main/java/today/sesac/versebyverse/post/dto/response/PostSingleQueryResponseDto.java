package today.sesac.versebyverse.post.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 단건 조회 응답 record.
 */
public record PostSingleQueryResponseDto(

        Long postId,

        Long authorId,

        String afterTitle,

        String afterContent,

        String nickname,

        LocalDateTime createdAt,

        String imgUrl,

        String conceptType,

        Boolean isMine

        // TODO comment와 reaction 부분 연결 필요
//    int commentCount;
//
//    int reactionCount;
//
//    Map<String, Integer> reactionDetailCount;

) {

    public static PostSingleQueryResponseDto of(Post post, Long memberId) {

        return new PostSingleQueryResponseDto(
                post.getId(),
                post.getAuthor().getId(),
                post.getAfterTitle(),
                post.getAfterContent(),
                post.getAuthor().getNickname(),
                post.getCreatedAt(),
                post.getImageUrl(),
                post.getConceptType().toString(),
                post.isMine(memberId)
        );
    }
}