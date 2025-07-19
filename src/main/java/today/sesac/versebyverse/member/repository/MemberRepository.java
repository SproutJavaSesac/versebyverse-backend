package today.sesac.versebyverse.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.SocialType;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndSocialType(String email, SocialType socialType);
}