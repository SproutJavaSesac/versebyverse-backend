package today.sesac.versebyverse.comment.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 댓글 응답 DTO.
 *
 * <p>댓글의 상세 정보를 포함하며, 재귀적으로 답글을 포함할 수 있습니다. 변환 로직 포함+ 필드와 인자가 달라 record가 아닌 class로 선언.</p>
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {

    private final Long commentId;

    private final Long commenterId;

    private final String commenterNickname;

    private final Long parentId;

    private final int order;

    private final int level;

    private final String content;

    private final int reactionCount;

    private final Emotion myReaction;

    private final Map<Emotion, Integer> reactions;

    private final Boolean isDeleted;

    private final Boolean isBlocked;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    /**
     * Comment 객체를 CommentResponseDto로 변환합니다 (리액션 정보 포함).
     *
     * @param comment       변환할 Comment 객체
     * @param order         순서
     * @param reactionCount 리액션 총 개수
     * @param reactions     리액션별 상세 개수
     * @return 변환된 CommentResponseDto 객체
     */
    public static CommentResponseDto of(Comment comment, int order, int reactionCount,
            Emotion myReaction,
            Map<Emotion, Integer> reactions) {

        return new CommentResponseDto(
                comment.getId(),
                comment.getDisplayCommenterId(),
                comment.getDisplayCommenterNickname(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                order,
                comment.getLevel(),
                comment.getDisplayContent(),
                reactionCount,
                myReaction,
                reactions,
                comment.isDeleted(),
                comment.isBlocked(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}