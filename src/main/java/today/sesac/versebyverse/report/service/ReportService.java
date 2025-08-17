package today.sesac.versebyverse.report.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.service.PostQueryService;
import today.sesac.versebyverse.report.dto.request.ReportRequestDto;
import today.sesac.versebyverse.report.dto.response.PostReportResponseDto;
import today.sesac.versebyverse.report.entity.Report;
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

    /**
     * 게시글을 신고하는 기능입니다.
     *
     * @param reportRequestDto 클라이언트로부터 입력받은 신고요청 dto
     * @param reporterId       신고자 id
     * @param postId           게시글 id
     * @return 게시글 신고 응답 dto
     */
    public PostReportResponseDto reportPost(ReportRequestDto reportRequestDto, Long reporterId,
            Long postId) {

        Member reporter = memberService.getActiveMemberOrThrow(reporterId);
        Post post = postService.getActivePostById(postId);

        //게시글 신고 등록 전 신고 가능한지 확인
        validatePostReportEligibility(reporter, post);

        Report report = reportRepository.save(
                Report.createPostReport(reporter, post, reportRequestDto.getReasonType(),
                        reportRequestDto.getReasonDetail())
        );

        return PostReportResponseDto.of(report.getId(), report.getReporter().getId(),
                report.getPost().getId(), report.getReasonType(), report.getReasonDetail(),
                report.getStatusType(), report.getCreatedAt());
    }

    /**
     * 게시글 신고를 등록하기 전 검증하는 기능입니다.
     *
     * @param reporter 신고자
     * @param post     게시글
     */
    private void validatePostReportEligibility(Member reporter, Post post) {

        validateDuplicatePostReport(reporter.getId(), post.getId());
        validateSelfPostReport(reporter, post);
    }

    /**
     * 게시글 중복 신고가 있는지 확인합니다.
     */
    private void validateDuplicatePostReport(Long reporterId, Long postId) {

        if (reportRepository.findByReporterIdAndPostId(reporterId, postId).isPresent()) {
            throw new ReportException(ReportErrorCode.DUPLICATE_REPORT, String.valueOf(postId));
        }
    }

    /**
     * 신고자 자신의 게시물을 신고하는지 확인합니다.
     */
    private void validateSelfPostReport(Member reporter, Post post) {

        if (reporter.getId().equals(post.getAuthor().getId())) {
            throw new ReportException(ReportErrorCode.SELF_REPORT_NOT_ALLOWED,
                    String.valueOf(post.getId()));
        }
    }
}
