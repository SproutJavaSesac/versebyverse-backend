package today.sesac.shoutify.post.dto.response;

import lombok.Getter;

@Getter
public class PostCreateResponseDto {
    private final Long postId;
    private final String title;
    private final String afterContents;

    public PostCreateResponseDto(Long postId, String title, String afterContents) {
        this.postId = postId;
        this.title = title;
        this.afterContents = afterContents;
    }

    /**
     * 성공응답
     */
    public static PostCreateResponseDto createSuccess(Long postId, String title,
                                                      String afterContents) {
        return new PostCreateResponseDto(postId, title, afterContents);
    }

    /**
     * 실패응답
     */
    public static PostCreateResponseDto failure() {
        return new PostCreateResponseDto(null, null, null);
    }
}
