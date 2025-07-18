package today.sesac.versebyverse.report.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 * 신고 처리 결과 응답 DTO.
 */
@Getter
@Builder
public class ReportActionResponseDto {

    private int reportId;

    private String statusType; // PENDING, ACCEPTED, REJECTED, ALREADY_DELETED

    private LocalDateTime updateAt;
}