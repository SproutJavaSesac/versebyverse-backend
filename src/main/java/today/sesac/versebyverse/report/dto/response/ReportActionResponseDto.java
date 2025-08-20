package today.sesac.versebyverse.report.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.report.entity.StatusType;

/**
 * 신고 처리 결과 응답 DTO.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ReportActionResponseDto {

    private Long reportId;

    private StatusType statusType; // PENDING, ACCEPTED, REJECTED, ALREADY_DELETED

    private LocalDateTime updateAt;
}