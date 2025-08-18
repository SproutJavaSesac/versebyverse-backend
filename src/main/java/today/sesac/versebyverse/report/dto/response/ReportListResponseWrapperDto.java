package today.sesac.versebyverse.report.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.report.entity.Report;

/**
 * 신고 목록 응답을 위한 래퍼 DTO입니다. 신고 목록과 페이지네이션 정보를 함께 제공합니다.
 */
@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
public class ReportListResponseWrapperDto {

    /**
     * 신고 목록입니다.
     */
    private List<ReportResponseDto> reports;

    /**
     * 페이지네이션 정보입니다.
     */
    private PaginationDto pagination;

    /**
     * {@code Page<Report>} 엔티티를 ReportListResponseWrapperDto로 변환합니다. 각 Report 엔티티를
     * ReportResponseDto로 변환하고 페이지네이션 정보를 포함합니다.
     *
     * @param page 신고 엔티티 페이지
     * @return 신고 목록과 페이지네이션 정보를 포함한 응답 DTO
     */
    public static ReportListResponseWrapperDto of(Page<Report> page) {

        List<ReportResponseDto> reportList = page.getContent().stream()
                .map(report -> ReportResponseDto.of(
                        report.getId(),
                        report.getReporter().getId(),
                        report.getPost() != null ? report.getPost().getId() : null,
                        report.getComment() != null ? report.getComment().getId() : null,
                        report.getReasonType(),
                        report.getReasonDetail(),
                        report.getStatusType(),
                        getReportCount(report),
                        report.getCreatedAt(),
                        report.getUpdatedAt()
                )).toList();

        PaginationDto pagination =
                new PaginationDto(page.getNumber(), page.getTotalPages(), page.getTotalElements(),
                        page.getSize(), page.hasNext(), page.hasPrevious());

        return new ReportListResponseWrapperDto(reportList, pagination);
    }

    /**
     * 신고된 게시글 또는 댓글의 누적 신고 횟수를 반환합니다. 게시글 신고인 경우 게시글의 신고 횟수를, 댓글 신고인 경우 댓글의 신고 횟수를 반환합니다.
     *
     * @param report 신고 엔티티
     * @return 신고된 대상의 누적 신고 횟수
     */
    private static Integer getReportCount(Report report) {

        if (report.getPost() != null) {
            return report.getPost().getReportCount();
        }
        return report.getComment().getReportCount();

    }

}