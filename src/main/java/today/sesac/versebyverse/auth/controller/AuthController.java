package today.sesac.versebyverse.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.dto.response.LoginStatusResponseDto;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;

/**
 * 로그인 등 인증/인가 기능을 담당하는 컨트롤러.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    // TODO: 프론트 테스트를 위한 로그인 상태 확인용 임시 메서드 - 수정 예정
    @GetMapping("/status")
    public ApiResponse<?> checkAuthStatus(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getMemberId();
        Member member = memberService.findById(memberId);
        String nickname = member.getNickname();
        String email = member.getEmail();

        LoginStatusResponseDto response = new LoginStatusResponseDto(true, memberId, nickname,
                email);

        return ApiResponse.success(response);
    }

}