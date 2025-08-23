package today.sesac.versebyverse.post.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.post.entity.PostProofreadTask;

@Repository
public interface PostProofreadTaskRepository extends JpaRepository<PostProofreadTask, Long> {

    /**
     * 게시글 교정 세션을 UUID로 조회합니다.
     *
     * @param taskUuid 게시글 교정 세션의 UUID
     * @return 게시글 교정 세션 엔티티
     */
    Optional<PostProofreadTask> findByUuid(String taskUuid);

    Optional<PostProofreadTask> findByUuidAndMemberId(String uuid, Long memberId);
}