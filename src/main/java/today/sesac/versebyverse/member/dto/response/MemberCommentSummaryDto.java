package today.sesac.versebyverse.member.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.comment.entity.Comment;

/**
 * 사용자가 작성한 댓글 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MemberCommentSummaryDto {

    private Long commentId;

    private Long postId;

    private String postTitle;

    private String afterContent;

    private int reactionCount;

    private LocalDateTime createdAt;

    /**
     * Comment 객체를 회원이 작성한 댓글 응답 DTO로 변환합니다.
     *
     * @param comment 변환할 comment 객체
     * @return 사용자가 작성한 댓글 응답 DTO
     */
    public static MemberCommentSummaryDto of(Comment comment) {

        return new MemberCommentSummaryDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getPost().getAfterTitle(),
                comment.getAfterContent(),
                3,  //TODO: 반응하기 미구현 관계로 하드코딩 - 삭제하기
                comment.getCreatedAt()
        );
    }
}
