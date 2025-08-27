package today.sesac.versebyverse.report.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.report.entity.ReasonType;
import today.sesac.versebyverse.report.entity.StatusType;

/**
 * 댓글 신고 응답 dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class CommentReportResponseDto {

    private Long reportId;

    private Long reporterId;

    private Long commentId;

    private ReasonType reasonType;

    private String reasonDetail;

    private StatusType statusType;

    private LocalDateTime createdAt;

}