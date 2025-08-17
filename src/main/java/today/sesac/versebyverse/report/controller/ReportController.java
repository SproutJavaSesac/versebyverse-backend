package today.sesac.versebyverse.report.controller;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.report.dto.request.ReportActionRequestDto;
import today.sesac.versebyverse.report.dto.request.ReportRequestDto;
import today.sesac.versebyverse.report.dto.response.AdminReportResponseDto;
import today.sesac.versebyverse.report.dto.response.CommentReportResponseDto;
import today.sesac.versebyverse.report.dto.response.PostReportResponseDto;
import today.sesac.versebyverse.report.dto.response.ReportActionResponseDto;
import today.sesac.versebyverse.report.dto.response.ReportListResponseWrapperDto;
import today.sesac.versebyverse.report.service.ReportService;

/**
 * 신고 컨트롤러.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {

    private String reportType = "";

    private final ReportService reportService;

    /**
     * 게시글 신고.
     *
     * @param postId           게시글 식별 id
     * @param reportRequestDto 요청 dto
     * @return 응답
     */
    @PostMapping("/reports/posts/{postId}")
    public ApiResponse<PostReportResponseDto> postReport(@PathVariable("postId") Long postId,
            @Valid @RequestBody ReportRequestDto reportRequestDto,
            @AuthenticationPrincipal UserPrincipal reporter) {

        return ApiResponse.success(
                reportService.reportPost(reportRequestDto, reporter.getMemberId(), postId));
    }

    /**
     * 댓글 신고.
     *
     * @param commentId        댓글 식별 id
     * @param reportRequestDto 요청 dto
     * @return 응답
     */
    @PostMapping("/reports/comments/{commentId}")
    public ApiResponse<?> commentReport(@PathVariable("commentId") int commentId,
            @RequestBody ReportRequestDto reportRequestDto) {

        reportType = "COMMENT";
        CommentReportResponseDto commentReportResponseDto = CommentReportResponseDto.builder()
                .reportId((int) (Math.random() * 10) + 1)
                .commentId(commentId)
                .reportType(reportType)
                .reasonDetail(reportRequestDto.getReasonDetail())
                .statusType("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
        return ApiResponse.success(commentReportResponseDto);
    }

    /**
     * 관리자용 신고 목록 조회.
     *
     * @param statusType 신고 상태 (PENDING/DECIDED)
     * @param page       페이지 번호
     * @param size       한 페이지당 아이템 수
     * @param sort       정렬 기준 (latest/oldest)
     * @return 신고 목록 + 페이지네이션 정보
     */
    @GetMapping("/admin/reports")
    public ApiResponse<?> getReportList(@RequestParam String statusType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "latest") String sort) {

        // 더미 리스트
        List<AdminReportResponseDto> reports = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            if (i % 2 == 0) {
                reportType = "COMMENT";
            } else {
                reportType = "POST";
            }
            reports.add(
                    AdminReportResponseDto.builder()
                            .reportId((int) (Math.random() * i * 10) + 1)
                            .reporterId(i)
                            .reportType(reportType)
                            .postId(i)
                            .reasonCode("PROFANITY")
                            .reasonDetail(null)
                            .statusType(statusType)
                            .createdAt(LocalDateTime.now())
                            .build()
            );
        }
        PaginationDto pagination = new PaginationDto(page, (int) Math.ceil(1000.0 / size),
                1000, size, page * size < 1000, page > 1);

        return ApiResponse.success(
                ReportListResponseWrapperDto.builder().reports(reports).pagination(pagination)
                        .build());
    }

    /**
     * 신고 처리.
     *
     * @param reportId               신고 ID
     * @param reportActionRequestDto 처리 요청 dto
     * @return 처리 결과
     */
    @PatchMapping("/admin/reports/{reportId}")
    public ApiResponse<?> handleReport(@PathVariable int reportId,
            @RequestBody ReportActionRequestDto reportActionRequestDto) {

        return ApiResponse.success(
                ReportActionResponseDto.builder().reportId(reportId).statusType(
                                reportActionRequestDto.getAction())
                        .updateAt(LocalDateTime.now()).build());
    }

}