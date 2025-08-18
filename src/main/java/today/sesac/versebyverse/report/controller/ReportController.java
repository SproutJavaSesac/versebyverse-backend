package today.sesac.versebyverse.report.controller;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
import today.sesac.versebyverse.report.dto.request.ReportActionRequestDto;
import today.sesac.versebyverse.report.dto.request.ReportRequestDto;
import today.sesac.versebyverse.report.dto.response.CommentReportResponseDto;
import today.sesac.versebyverse.report.dto.response.PostReportResponseDto;
import today.sesac.versebyverse.report.dto.response.ReportActionResponseDto;
import today.sesac.versebyverse.report.dto.response.ReportListResponseWrapperDto;
import today.sesac.versebyverse.report.entity.StatusType;
import today.sesac.versebyverse.report.service.ReportService;

/**
 * 신고 컨트롤러.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {

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
    public ApiResponse<CommentReportResponseDto> commentReport(
            @PathVariable("commentId") long commentId,
            @Valid @RequestBody ReportRequestDto reportRequestDto,
            @AuthenticationPrincipal UserPrincipal reporter) {

        CommentReportResponseDto response =
                reportService.reportComment(reportRequestDto, reporter.getMemberId(), commentId);
        return ApiResponse.success(response);
    }

    /**
     * 관리자용 신고 목록 조회. 신고 상태별로 필터링하여 신고 목록을 페이지네이션과 함께 조회합니다.
     *
     * @param statusType 신고 상태 (PENDING: 대기중, ACCEPTED: 승인, REJECTED: 거부, POSTPONE: 보류)
     * @param page       페이지 번호 (0부터 시작, 기본값: 0)
     * @param size       한 페이지당 아이템 수 (기본값: 20)
     * @param sort       정렬 기준 필드 (기본값: createdAt)
     * @param order      정렬 순서 (ASC: 오름차순, DESC: 내림차순, 기본값: DESC)
     * @return 신고 목록과 페이지네이션 정보를 포함한 응답
     */
    @GetMapping("/admin/reports")
    public ApiResponse<ReportListResponseWrapperDto> getReportList(
            @RequestParam(required = false) StatusType statusType,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(name = "order", defaultValue = "DESC") Direction order) {

        return ApiResponse.success(
                reportService.getReportList(statusType, PageRequest.of(page, size, order, sort)));
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