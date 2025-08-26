package today.sesac.versebyverse.report.dto.request;

import lombok.Getter;
import today.sesac.versebyverse.report.entity.StatusType;

/**
 * 신고 처리 요청 DTO (ACCEPT / REJECT).
 */
@Getter
public class ReportActionRequestDto {

    private StatusType action;
}