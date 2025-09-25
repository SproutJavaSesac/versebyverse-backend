package today.sesac.versebyverse.report.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.comment.service.CommentService;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.service.PostQueryService;
import today.sesac.versebyverse.report.dto.request.ReportActionRequestDto;
import today.sesac.versebyverse.report.dto.request.ReportRequestDto;
import today.sesac.versebyverse.report.dto.response.CommentReportResponseDto;
import today.sesac.versebyverse.report.dto.response.PostReportResponseDto;
import today.sesac.versebyverse.report.dto.response.ReportListResponseWrapperDto;
import today.sesac.versebyverse.report.dto.response.ReportResponseDto;
import today.sesac.versebyverse.report.entity.Report;
import today.sesac.versebyverse.report.entity.ReportType;
import today.sesac.versebyverse.report.entity.StatusType;
import today.sesac.versebyverse.report.exception.ReportErrorCode;
import today.sesac.versebyverse.report.exception.ReportException;
import today.sesac.versebyverse.report.repository.ReportRepository;

/**
 * 신고 서비스입니다. 게시글신고, 댓글신고, 신고상태 별 신고 목록 조회, 신고 처리를 담당합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;

    private final MemberService memberService;

    private final PostQueryService postService;

    private final CommentService commentService;

    /**
     * 게시글을 신고합니다.
     *
     * @param reportRequestDto 클라이언트로부터 입력받은 신고요청 dto
     * @param reporterId       신고자 id
     * @param postId           게시글 id
     * @return 게시글 신고 응답 dto
     * @throws ReportException 중복 신고, 자신의 게시글 신고, 게시글이 존재하지 않는 경우
     */
    public PostReportResponseDto reportPost(ReportRequestDto reportRequestDto,
            Long reporterId, Long postId) {

        Member reporter = memberService.getActiveMemberOrThrow(reporterId);
        Post post = postService.getActivePostById(postId);

        //게시글 신고 등록 전 신고 가능한지 확인
        validatePostReportEligibility(reporter, post);

        Report report = reportRepository.save(
                Report.createPostReport(reporter, post, reportRequestDto.getReasonType(),
                        reportRequestDto.getReasonDetail())
        );

        post.increaseReportCount();

        return PostReportResponseDto.of(report.getId(), report.getReporter().getId(),
                report.getPost().getId(), report.getReasonType(), report.getReasonDetail(),
                report.getStatusType(), report.getCreatedAt());
    }

    /**
     * 댓글을 신고합니다.
     *
     * @param reportRequestDto 신고 요청 DTO
     * @param reporterId       신고자 ID
     * @param commentId        댓글 ID
     * @return 신고 응답 DTO
     * @throws ReportException 중복 신고, 자신의 댓글 신고, 댓글이 존재하지 않는 경우
     */
    public CommentReportResponseDto reportComment(
            ReportRequestDto reportRequestDto, Long reporterId, Long commentId) {

        Member reporter = memberService.getActiveMemberOrThrow(reporterId);
        Comment comment = commentService.getActiveCommentById(commentId);

        // 댓글 신고 등록 전 신고 가능한지 확인
        validateCommentReportEligibility(reporter, comment);

        Report report = reportRepository.save(
                Report.createCommentReport(reporter, comment, reportRequestDto.getReasonType(),
                        reportRequestDto.getReasonDetail())
        );

        comment.increaseReportCount();

        return CommentReportResponseDto.of(report.getId(),
                report.getReporter().getId(),
                report.getComment().getId(),
                report.getReasonType(), report.getReasonDetail(),
                report.getStatusType(), report.getCreatedAt());
    }

    /**
     * 댓글 신고 가능 여부를 확인합니다.
     *
     * @param reporter 신고자
     * @param comment  댓글
     */
    private void validateCommentReportEligibility(Member reporter, Comment comment) {

        validateDuplicateCommentReport(reporter.getId(), comment.getId());
        validateSelfCommentReport(reporter, comment);
    }

    /**
     * 게시글 신고 가능 여부를 확인합니다.
     *
     * @param reporter 신고자
     * @param post     게시글
     * @throws ReportException 중복 신고, 자신의 게시글 신고인 경우
     */
    private void validatePostReportEligibility(Member reporter, Post post) {

        validateDuplicatePostReport(reporter.getId(), post.getId());
        validateSelfPostReport(reporter, post);
    }

    /**
     * 게시글 중복 신고가 있는지 확인합니다.
     *
     * @param reporterId 신고자 ID
     * @param postId     게시글 ID
     * @throws ReportException 이미 신고한 게시글인 경우
     */
    private void validateDuplicatePostReport(Long reporterId, Long postId) {

        if (reportRepository.findByReporterIdAndPostId(reporterId, postId).isPresent()) {
            throw new ReportException(ReportErrorCode.DUPLICATE_REPORT, "postId");
        }
    }

    /**
     * 신고자 자신의 게시물을 신고하는지 확인합니다.
     *
     * @param reporter 신고자
     * @param post     게시글
     * @throws ReportException 자신의 게시글을 신고하려는 경우
     */
    private void validateSelfPostReport(Member reporter, Post post) {

        if (reporter.getId().equals(post.getAuthor().getId())) {
            throw new ReportException(ReportErrorCode.SELF_REPORT_NOT_ALLOWED,
                    "postId");
        }
    }

    /**
     * 댓글 중복 신고가 있는지 확인합니다.
     *
     * @param reporterId 신고자 ID
     * @param commentId  댓글 ID
     * @throws ReportException 이미 신고한 댓글인 경우
     */
    private void validateDuplicateCommentReport(Long reporterId, Long commentId) {

        if (reportRepository.findByReporterIdAndCommentId(reporterId, commentId).isPresent()) {
            throw new ReportException(ReportErrorCode.DUPLICATE_REPORT, "commentId");
        }
    }

    /**
     * 신고자 자신의 댓글을 신고하는지 확인합니다.
     *
     * @param reporter 신고자
     * @param comment  댓글
     * @throws ReportException 자신의 댓글을 신고하려는 경우
     */
    private void validateSelfCommentReport(Member reporter, Comment comment) {

        if (reporter.getId().equals(comment.getCommenter().getId())) {
            throw new ReportException(ReportErrorCode.SELF_REPORT_NOT_ALLOWED,
                    "commentId");
        }
    }

    /**
     * 신고 목록을 조회합니다. 신고 상태(StatusType)에 따라 필터링하여 페이지네이션된 신고 목록을 반환합니다. statusType이 null인 경우 모든 상태의
     * 신고를 조회합니다.
     *
     * @param statusType  신고 상태 (PENDING: 대기중, ACCEPTED: 승인, REJECTED: 거부, POSTPONE: 보류)
     * @param pageRequest 페이지네이션 정보 (페이지 번호, 크기, 정렬 기준)
     * @return 신고 목록과 페이지네이션 정보를 포함한 응답 DTO
     */
    public ReportListResponseWrapperDto getReportList(StatusType statusType,
            PageRequest pageRequest) {

        Page<Report> reportPage = reportRepository.findAllByStatusType(statusType, pageRequest);

        return ReportListResponseWrapperDto.of(reportPage);
    }

    /**
     * 신고에 대한 관리자 액션을 처리합니다.
     *
     * <p>이 메서드는 다음과 같은 작업을 수행합니다:</p>
     * <ul>
     *   <li>신고 존재 여부 및 처리 상태 확인</li>
     *   <li>신고 유형에 따른 적절한 조치 실행</li>
     *   <li>신고 처리 결과 반환</li>
     * </ul>
     *
     * <p>신고가 승인된 경우:</p>
     * <ul>
     *   <li>게시글 신고: 해당 게시글을 차단</li>
     *   <li>댓글 신고: 해당 댓글을 차단</li>
     * </ul>
     *
     * <p>신고가 거부된 경우:</p>
     * <ul>
     *   <li>신고 상태만 REJECTED로 변경</li>
     * </ul>
     *
     * @param reportId               처리할 신고의 ID
     * @param reportActionRequestDto 신고 처리 액션 정보 (승인/거부)
     * @return 처리된 신고 정보를 담은 응답 DTO
     */
    @Transactional
    public ReportResponseDto handleReportAction(Long reportId,
            ReportActionRequestDto reportActionRequestDto) {
        // 신고 존재 여부 확인
        Report report = reportRepository.findById(reportId)
                .orElseThrow(
                        () -> new ReportException(ReportErrorCode.REPORT_NOT_FOUND, "reportId"));

        // 이미 처리된 신고인지 확인
        if (report.getStatusType() != StatusType.PENDING) {
            throw new ReportException(ReportErrorCode.REPORT_ALREADY_PROCESSED, "reportId");
        }

        ReportType reportType = report.checkReportType();
        StatusType statusType = reportActionRequestDto.getAction();
        switch (statusType) {
            case ACCEPTED -> {
                report.accept();
                Post post = report.getPost();
                Comment comment = report.getComment();
                if (post == null) {
                    comment.block();
                } else {
                    post.block();
                }
            }
            case REJECTED -> report.reject();
            default ->
                    throw new ReportException(ReportErrorCode.REPORT_ACTION_TYPE_WRONG, "action");

        }

        return reportType == ReportType.POST ? ReportResponseDto.createPostReportResponse(report)
                : ReportResponseDto.createCommentReportResponse(report);
    }

}
