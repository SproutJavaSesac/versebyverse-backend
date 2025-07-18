package today.sesac.versebyverse.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.SocialType;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndSocialType(String email, SocialType socialType);

    /**
     * 회원 ID로 회원 정보를 조회합니다. 삭제되지 않은 회원만 조회합니다.
     *
     * @param memberId 회원 ID
     * @return {@code Optional<Member>} 삭제되지 않은 회원 정보.
     */
    Optional<Member> findByIdAndIsDeletedFalse(Long memberId);
}