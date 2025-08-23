package today.sesac.versebyverse.post.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 단건 조회 응답 record.
 */
public record PostSingleQueryResponseDto(

        Long postId,

        String afterTitle,

        String afterContent,

        String nickname,

        LocalDateTime createdAt,

        String imgUrl,

        String genreType,

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
                post.getAfterTitle(),
                post.getAfterContent(),
                post.getAuthor().getNickname(),
                post.getCreatedAt(),
                post.getImageUrl(),
                post.getGenreType().toString(),
                post.isMine(memberId)
        );
    }
}