package today.sesac.shoutify.post.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 게시글 단건 조회 응답 dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostSingleQueryResponseDto {

    private final Long postId;

    private final String title;

    private final String afterContent;

    private final String nickname;

    private final LocalDateTime createdAt;

    private final String imgUrl;

    private final String conceptType;

    private final boolean isMine;

    // TODO comment와 reaction 부분 연결 필요
//    private final int commentCount;
//
//    private final int reactionCount;
//
//    private final Map<String, Integer> reactionDetailCount;

}
