package today.sesac.shoutify.report.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import today.sesac.shoutify.global.response.PaginationDto;

/**
 * 신고 목록 응달 dto.
 */
@Data
@Builder
public class ReportListResponseWrapperDto {


    /**
     * 신고 리스트.
     */
    private List<AdminReportResponseDto> reports;

    /**
     * 페이지네이션 정보.
     */
    private PaginationDto pagination;
}
