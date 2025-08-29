package today.sesac.versebyverse.post.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.post.entity.PostProofreadTask;

/**
 * PostProofreadTask 엔티티에 대한 JPA 리포지토리입니다.
 */
@Repository
public interface PostProofreadTaskRepository extends JpaRepository<PostProofreadTask, Long> {

    /**
     * 주어진 UUID와 회원 ID에 해당하는 PostProofreadTask를 조회합니다.
     *
     * @param uuid     작업의 고유 식별자
     * @param memberId 회원의 고유 식별자
     * @return 해당하는 PostProofreadTask가 존재하면 Optional에 담아 반환, 없으면 빈 Optional 반환
     */
    Optional<PostProofreadTask> findByUuidAndMemberId(String uuid, Long memberId);
}