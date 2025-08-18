package today.sesac.versebyverse.report.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.report.entity.ReasonType;
import today.sesac.versebyverse.report.entity.StatusType;

/**
 * 관리자용 신고 목록 응답 DTO입니다.
 * 신고된 게시글 또는 댓글의 상세 정보와 신고 관련 정보를 제공합니다.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ReportResponseDto {

    private Long reportId;

    private Long reporterId;

    private Long postId;

    private Long commentId;

    private ReasonType reasonType;

    private String reasonDetail; // 직접 입력

    private StatusType statusType;

    private int reportCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}