package today.sesac.shoutify.post.dto.response;

import lombok.Getter;

@Getter
public class PostCreateResponse {
    private final Long postId;
    private final String title;
    private final String afterContents;

    public PostCreateResponse(Long postId, String title, String afterContents) {
        this.postId = postId;
        this.title = title;
        this.afterContents = afterContents;
    }

    /**
     * 성공응답
     */
    public static PostCreateResponse createSuccess(Long postId, String title,
                                                   String afterContents) {
        return new PostCreateResponse(postId, title, afterContents);
    }

    /**
     * 실패응답
     */
    public static PostCreateResponse failure() {
        return new PostCreateResponse(null, null, null);
    }
}
