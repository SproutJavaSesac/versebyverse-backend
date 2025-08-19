package today.sesac.versebyverse.report.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.report.entity.ReasonType;
import today.sesac.versebyverse.report.entity.StatusType;

/**
 * 게시글 신고 응답 DTO.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostReportResponseDto {

    private long reportId;

    private long reporterId;

    private long postId;

    private ReasonType reasonType;

    private String reasonDetail;

    private StatusType statusType;

    private LocalDateTime createdAt;
}