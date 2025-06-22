package today.sesac.shoutify.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
}
