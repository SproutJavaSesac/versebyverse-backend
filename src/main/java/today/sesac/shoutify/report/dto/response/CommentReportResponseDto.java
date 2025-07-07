package today.sesac.shoutify.report.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 * 댓글 신고 요청 dto.
 */
@Getter
@Builder
public class CommentReportResponseDto {

    private int reportId;

    private int commentId;

    private String reportType; // POST

    private String reasonType;

    private String reasonDetail;

    private String statusType;

    private LocalDateTime createdAt;
}
