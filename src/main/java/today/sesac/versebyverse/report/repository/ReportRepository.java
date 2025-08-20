package today.sesac.versebyverse.report.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.report.entity.Report;
import today.sesac.versebyverse.report.entity.StatusType;

/**
 * 신고 레포지토리입니다.
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    /**
     * 신고자 ID와 게시글 ID를 통해 Report 엔티티를 조회합니다.
     *
     * @param reporterId 신고자 id
     * @param postId     게시글 id
     */
    Optional<Report> findByReporterIdAndPostId(Long reporterId, Long postId);

    /**
     * 신고자의 댓글 중복 신고가 있는지 조회합니다.
     *
     * @param reporterId 신고자 id
     * @param commentId  댓글 id
     */
    Optional<Report> findByReporterIdAndCommentId(Long reporterId, Long commentId);

    /**
     * 신고 상태별로 신고 목록을 페이지네이션과 함께 조회합니다. statusType이 null인 경우 모든 상태의 신고를 조회하고, 특정 상태가 지정된 경우 해당 상태의
     * 신고만 조회합니다.
     *
     * @param statusType 신고 상태 (PENDING: 대기중, ACCEPTED: 승인, REJECTED: 거부, POSTPONE: 보류)
     * @param pageable   페이지네이션 정보 (페이지 번호, 크기, 정렬 기준)
     * @return 페이지네이션된 신고 목록
     */
    @Query("SELECT r FROM Report r WHERE (:statusType IS NULL OR r.statusType = :statusType)")
    Page<Report> findAllByStatusType(@Param("statusType") StatusType statusType, Pageable pageable);

    /**
     * 신고의 상태를 업데이트합니다.
     *
     * @param reportId   업데이트할 신고의 ID
     * @param statusType 설정할 새로운 상태 타입
     */
    @Modifying
    @Query("update Report r set r.statusType = :statusType where r.id = :reportId")
    Optional<Report> updateStatus(@Param("reportId") Long reportId,
            @Param("statusType") StatusType statusType);

}
