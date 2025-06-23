package today.sesac.shoutify.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.SocialType;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmailAndSocialType(String email, SocialType socialType);
}
