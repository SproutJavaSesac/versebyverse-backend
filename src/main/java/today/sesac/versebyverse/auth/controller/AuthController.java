package today.sesac.versebyverse.auth.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.dto.response.LoginStatusResponseDto;
import today.sesac.versebyverse.auth.service.AuthService;
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

    private final AuthService authService;

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

    /**
     * 탈퇴 요청을 수행합니다.
     *
     * @param userPrincipal 요청한 회원의 인증 정보.
     * @return 성공 메시지.
     */
    @DeleteMapping("/members/withdraw")
    public ApiResponse<String> withdraw(@AuthenticationPrincipal UserPrincipal userPrincipal, 
                                        HttpServletRequest request, 
                                        HttpServletResponse response) {

        // 1. 회원 탈퇴(소셜 로그인 연동 해제, db에서 회원 삭제)
        authService.withdraw(userPrincipal.getMemberId(), userPrincipal.getName());

        // 2. 로그아웃(세션 무효화 및 쿠키 삭제)
        request.getSession().invalidate();

        Cookie cookie = new Cookie("JSESSIONID", "");   // TODO: https 적용할 때 변경하기. 쿠키 설정할 때 같이 변경
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ApiResponse.success("회원 탈퇴가 완료되었습니다.");
    }
}