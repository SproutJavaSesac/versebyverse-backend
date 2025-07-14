package today.sesac.shoutify.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.SocialType;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndSocialType(String email, SocialType socialType);

    /**
     * 특정 회원이 존재하는지 확인합니다. 활성 상태의 회원만 확인합니다.
     *
     * @param memberId 회원 ID
     * @return 해당 회원이 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByIdAndIsDeletedFalse(Long memberId);
}