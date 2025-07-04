package today.sesac.shoutify.comment.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.shoutify.comment.entity.Comment;
import today.sesac.shoutify.global.domain.Emotion;

/**
 * 댓글 응답 DTO.
 *
 * <p>댓글의 상세 정보를 포함하며, 재귀적으로 답글을 포함할 수 있습니다. 변환 로직 포함+ 필드와 인자가 달라 record가 아닌 class로 선언.</p>
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {

    private final Long id;

    private final Long commenterId;

    private final String commenterNickname;

    private final Long parentId;

    private final String content;

    private final int reactionCount;

    private final Map<Emotion, Integer> reactions;

    private final boolean isDeleted;

    private final boolean isReported;

    private final int level;

    private final List<CommentResponseDto> replies;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    /**
     * Comment 객체를 CommentResponseDto로 변환합니다.
     *
     * @param comment 변환할 Comment 객체
     * @return 변환된 CommentResponseDto 객체
     */
    public static CommentResponseDto of(Comment comment) {

        // 재귀적으로 모든 Comment 객체를 CommentResponseDto로 변환
        List<CommentResponseDto> replies = comment.getReplies().stream()
                .map(CommentResponseDto::of)
                .toList();

        return new CommentResponseDto(
                comment.getId(),
                comment.getDisplayCommenterId(),
                comment.getDisplayCommenterNickname(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                comment.getDisplayContent(),
                6, // TODO: reactionCount 필드 구현 필요
                Map.of(
                        Emotion.HAPPY, 3,
                        Emotion.SAD, 1,
                        Emotion.ANGRY, 0,
                        Emotion.EXCITED, 2
                ), // TODO: reactions 필드 구현 필요
                comment.isDeleted(),
                comment.isReported(),
                comment.getLevel(),
                replies,
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
