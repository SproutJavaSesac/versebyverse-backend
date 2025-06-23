package today.sesac.shoutify.post.dto.response;

import lombok.Getter;

@Getter
public class PostCreateResponse {
    private final boolean success;
    private final Long postId;
    private final String title;
    private final String afterContents;

    public PostCreateResponse(boolean success, Long postId, String title, String afterContents) {
        this.success = success;
        this.postId = postId;
        this.title = title;
        this.afterContents = afterContents;
    }

    /**
     * 성공응답
     */
    public static PostCreateResponse createSuccess(Long postId, String title,
                                                   String afterContents) {
        return new PostCreateResponse(true, postId, title, afterContents);
    }

    /**
     * 실패응답
     */
    public static PostCreateResponse failure() {
        return new PostCreateResponse(false, null, null, null);
    }
}
