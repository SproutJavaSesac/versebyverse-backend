package today.sesac.shoutify.post.dto.response;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 게시글 단건 조회 응답 dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostSingleQueryResponseDto {

    @NotNull
    private final Long postId;

    @NotNull
    private final String title;

    @NotNull
    private final String afterContent;

    @NotNull
    private final String nickname;

    @NotNull
    private final LocalDateTime createdAt;

    private final String imgUrl;

    @NotNull
    private final String conceptType;

    @NotNull
    private final boolean isMine;

    // TODO comment와 reaction 부분 연결 필요
//    private final int commentCount;
//
//    private final int reactionCount;
//
//    private final Map<String, Integer> reactionDetailCount;

}
