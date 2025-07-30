package today.sesac.versebyverse.post.dto.response;

import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 작성 응답 record.
 */
public record PostCreateResponseDto(

        Long postId,

        String afterTitle,

        String afterContent
) {
    public static PostCreateResponseDto of(Post post) {
        return new PostCreateResponseDto(
                post.getId(),
                post.getAfterTitle(),
                post.getAfterContent()
        );
    }
}