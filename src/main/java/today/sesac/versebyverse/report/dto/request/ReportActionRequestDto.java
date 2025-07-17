package today.sesac.versebyverse.report.dto.request;

import lombok.Getter;

/**
 * 신고 처리 요청 DTO (ACCEPT / REJECT).
 */
@Getter
public class ReportActionRequestDto {

    private String action;
}