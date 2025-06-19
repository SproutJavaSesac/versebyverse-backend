package today.sesac.shoutify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.sesac.shoutify.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
