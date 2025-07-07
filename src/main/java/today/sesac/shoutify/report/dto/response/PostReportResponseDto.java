package today.sesac.shoutify.report.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 * 게시글 신고 응답 DTO.
 */
@Getter
@Builder
public class PostReportResponseDto {

    private int reportId;

    private int postId;

    private String reportType; // POST

    private String reasonType;

    private String reasonDetail;

    private String statusType;

    private LocalDateTime createdAt;
}
