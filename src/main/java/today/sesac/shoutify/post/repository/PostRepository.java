package today.sesac.shoutify.post.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.shoutify.post.entity.Post;

/**
 * 게시물에 대한 CRUD 작업을 수행하는 레포지토리 인터페이스입니다. 이 인터페이스는 Spring Data JPA를 사용하여 데이터베이스와 상호작용합니다.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 게시물 ID로 게시물을 조회합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물만 조회합니다.
     *
     * @param postId 게시물 ID
     * @return 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물의 Optional
     */
    Optional<Post> findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(Long postId);
}
