package today.sesac.shoutify.report.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 게시글 또는 댓글 신고 요청 DTO.
 */
@Getter
public class ReportRequestDto {

    /**
     * 신고 사유 .
     */
    @NotBlank
    private String reasonType;

    /**
     * '직접 입력'인 경우 사유 상세.
     */
    private String reasonDetail;
}