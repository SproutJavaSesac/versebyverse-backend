package today.sesac.versebyverse.report.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.report.entity.ReasonType;
import today.sesac.versebyverse.report.entity.Report;
import today.sesac.versebyverse.report.entity.ReportType;
import today.sesac.versebyverse.report.entity.StatusType;

/**
 * 관리자용 신고 목록 응답 DTO입니다. 신고된 게시글 또는 댓글의 상세 정보와 신고 관련 정보를 제공합니다.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ReportResponseDto {

    private Long reportId;

    private Long reporterId;

    private String reporterNickname;

    private Long postId;

    private Long commentId;

    private ReportType reportType;

    private ReasonType reasonType;

    private String reasonDetail; // 직접 입력

    private StatusType statusType;

    private int reportCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * 댓글 신고 응답 DTO를 생성합니다.
     *
     * @param report 신고 엔티티
     * @return 댓글 신고 응답 DTO
     */
    public static ReportResponseDto createCommentReportResponse(Report report) {

        return new ReportResponseDto(report.getId(),
                report.getReporter().getId(),
                report.getReporter().getNickname(),
                report.getComment().getPost().getId(),
                report.getComment().getId(),
                ReportType.COMMENT,
                report.getReasonType(),
                report.getReasonDetail(),
                report.getStatusType(),
                report.getComment().getReportCount(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );
    }

    /**
     * 게시글 신고 응답 DTO를 생성합니다.
     *
     * @param report 신고 엔티티
     * @return 게시글 신고 응답 DTO
     */
    public static ReportResponseDto createPostReportResponse(Report report) {

        return new ReportResponseDto(report.getId(),
                report.getReporter().getId(),
                report.getReporter().getNickname(),
                report.getPost().getId(),
                null,
                ReportType.POST,
                report.getReasonType(),
                report.getReasonDetail(),
                report.getStatusType(),
                report.getPost().getReportCount(),
                report.getCreatedAt(),
                report.getUpdatedAt());
    }

}