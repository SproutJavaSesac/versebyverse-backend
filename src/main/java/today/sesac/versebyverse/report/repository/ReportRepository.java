package today.sesac.versebyverse.report.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.report.entity.Report;

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
}
