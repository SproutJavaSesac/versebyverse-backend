package today.sesac.shoutify.comment.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.shoutify.comment.entity.Comment;
import today.sesac.shoutify.global.domain.Emotion;

/**
 * 댓글 작성 응답 DTO.
 */
@Getter
@AllArgsConstructor(staticName = "of") // TODO PRivate 변경 필요
public class CommentCreateResponseDto {

    private final Long id;

    private final Long postId;

    private final Long commenterId;

    private final String commenterNickname;

    private final Long parentId;

    private final String afterContent;

    private final int level;

    private final List<CommentCreateResponseDto> replies;

    private final int reactionTotalCount;

    private final Map<Emotion, Integer> reactions; // TODO: 리액션 기능 추가 예정

    private final LocalDateTime createdAt;

    /**
     * 댓글 엔티티로부터 첫 번째 레벨의 댓글 응답 DTO를 생성합니다.
     *
     * @param comment 댓글 엔티티
     * @return 첫 번째 레벨의 댓글 응답 DTO
     */
    public static CommentCreateResponseDto createFirstCommentFrom(Comment comment) {

//        List<CommentCreateResponseDto> replies = comment.getReplies().stream()
//                .map(CommentCreateResponseDto::createFirstCommentFrom)
//                .toList();

        return new CommentCreateResponseDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getCommenter().getId(),
                comment.getCommenter().getNickname(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                comment.getAfterContent(),
                comment.getLevel(),
                List.of(), // TODO replies 로직 검토 후 대체 필요
                6, // comment.getReactions().size(),
                Map.of(), // TODO: 리액션 기능 추가 예정
                comment.getCreatedAt()
        );
    }
}