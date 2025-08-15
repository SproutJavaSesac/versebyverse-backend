package today.sesac.versebyverse.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.SocialType;

/**
 * 회원 관련 레포지토리 인터페이스.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 회원 ID로 회원 정보를 조회합니다. 삭제되지 않은 회원만 조회합니다.
     *
     * @param memberId 회원 ID
     * @return {@code Optional<Member>} 삭제되지 않은 회원 정보.
     */
    Optional<Member> findByIdAndIsDeletedFalse(Long memberId);

    /**
     * email과 소셜 로그인 타입(ex. 카카오, 구글)으로 회원 정보를 조회합니다. 삭제되지 않은 회원만 조회합니다.
     *
     * @param email 회원의 이메일
     * @param socialType 소셜 로그인 타입(ex. 카카오, 구글)
     * @return {@code Optional<Member>} 삭제되지 않은 회원 정보.
     */
    Optional<Member> findByEmailAndSocialTypeAndIsDeletedFalse(String email, SocialType socialType);

    /**
     * 특정 회원이 존재하는지 확인합니다. 활성 상태의 회원만 확인합니다.
     *
     * @param memberId 회원 ID
     * @return 해당 회원이 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByIdAndIsDeletedFalse(Long memberId);
}