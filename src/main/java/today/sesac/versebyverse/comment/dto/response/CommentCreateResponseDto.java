package today.sesac.versebyverse.comment.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 댓글 작성 응답 DTO.
 */
@Getter
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "testOf")
public class CommentCreateResponseDto {

    private final Long commentId;

    private final Long postId;

    private final Long commenterId;

    private final String commenterNickname;

    private final Long parentId;

    private final String content;

    private final int level;

    private final int reactionTotalCount;

    private final Map<Emotion, Integer> reactionDetails;

    private final Boolean isDeleted;

    private final Boolean isBlocked;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    /**
     * 댓글 엔티티로부터 첫 번째 레벨의 댓글 응답 DTO를 생성합니다.
     *
     * @param comment 댓글 엔티티
     * @return 첫 번째 레벨의 댓글 응답 DTO
     */
    public static CommentCreateResponseDto of(Comment comment, int reacitonTotalCount,
            Map<Emotion, Integer> reactionDetails) {

        return new CommentCreateResponseDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getCommenter().getId(),
                comment.getCommenter().getNickname(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                comment.getAfterContent(),
                comment.getLevel(),
                reacitonTotalCount,
                reactionDetails,
                comment.isDeleted(),
                comment.isBlocked(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}