package today.sesac.shoutify.report.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 * 관리자용 신고 목록 응답 DTO.
 */
@Getter
@Builder
public class AdminReportResponseDto {

    private int reportId;

    private int reporterId;

    private String reportType; // POST / COMMENT

    private int postId;

    private int commentId;

    private String reasonCode;

    private String reasonDetail; // 직접 입력

    private String statusType;

    private LocalDateTime createdAt;
}