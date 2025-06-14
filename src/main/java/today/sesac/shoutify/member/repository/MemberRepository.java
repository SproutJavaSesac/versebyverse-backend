package today.sesac.shoutify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import today.sesac.shoutify.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
