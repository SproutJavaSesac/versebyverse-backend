package today.sesac.shoutify.post.dto.response;

import lombok.Getter;

@Getter
public class PostCreateResponseDto {
    private final Long postId;
    private final String title;
    private final String afterContent;

    public PostCreateResponseDto(Long postId, String title, String afterContent) {
        this.postId = postId;
        this.title = title;
        this.afterContent = afterContent;
    }

    /**
     * 성공응답
     */
    public static PostCreateResponseDto createSuccess(Long postId, String title,
                                                      String afterContent) {
        return new PostCreateResponseDto(postId, title, afterContent);
    }

    /**
     * 실패응답
     */
    public static PostCreateResponseDto failure() {
        return new PostCreateResponseDto(null, null, null);
    }
}
