package today.sesac.versebyverse.report.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import today.sesac.versebyverse.report.entity.ReasonType;

/**
 * 게시글 또는 댓글 신고 요청 DTO.
 */
@Getter
public class ReportRequestDto {

    /**
     * 신고 사유.
     */
    @NotNull
    private ReasonType reasonType;

    /**
     * '직접 입력'인 경우 사유 상세.
     */
    @Length(max = 500, message = "신고 사유가 500자 초과입니다.")
    private String reasonDetail;
}