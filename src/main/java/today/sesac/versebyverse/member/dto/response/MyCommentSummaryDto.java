package today.sesac.versebyverse.member.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import today.sesac.versebyverse.comment.entity.Comment;

@Getter
@AllArgsConstructor
public class MyCommentSummaryDto {

    private Long commentId;

    private Long postId;

    private String postTitle;

    private String beforeContent;

    private String afterContent;

    private int reactionCount;

    private LocalDateTime createdAt;

    public static MyCommentSummaryDto of(Comment comment) {

        return new MyCommentSummaryDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getPost().getAfterTitle(),
                comment.getBeforeContent(),
                comment.getAfterContent(),
                3,  //TODO: 반응하기 미구현 관계로 하드코딩 - 삭제하기
                comment.getCreatedAt()
        );
    }
}