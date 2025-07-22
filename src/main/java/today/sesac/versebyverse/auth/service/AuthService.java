package today.sesac.versebyverse.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.auth.dto.response.LoginStatusResponseDto;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;

/**
 * 로그인 등 인증/인가 기능을 담당하는 서비스.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;

    public LoginStatusResponseDto getLoginStatus(Long memberId) {

        Member member = memberService.findById(memberId);
        String nickname = member.getNickname();
        String email = member.getEmail();

        return LoginStatusResponseDto.createAuthenticated(memberId, nickname, email);
    }

}
