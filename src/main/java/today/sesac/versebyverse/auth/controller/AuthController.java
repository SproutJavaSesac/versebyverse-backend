package today.sesac.versebyverse.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.dto.response.LoginStatusResponseDto;
import today.sesac.versebyverse.auth.dto.response.UpdateRoleResponseDto;
import today.sesac.versebyverse.auth.service.AuthService;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.global.exception.InvalidProfileException;
import today.sesac.versebyverse.global.response.ApiResponse;

/**
 * 로그인 등 인증/인가 기능을 담당하는 컨트롤러.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${spring.profiles.active:default}")
    private String profile;

    /**
     * 사용자의 인증 여부 및 인증 상태를 확인할 때 필요한 정보를 반환합니다.
     *
     * @param userPrincipal 사용자의 인증 정보
     * @return 로그인 상태 반환 DTO
     */
    @GetMapping("/status")
    public ApiResponse<LoginStatusResponseDto> getLoginStatus(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        // 미인증된 사용자일 경우 - isAuthenticated를 false로 설정하고 응답
        if (userPrincipal == null) {
            LoginStatusResponseDto loginStatusResponseDto =
                    LoginStatusResponseDto.createUnauthenticated();
            return ApiResponse.success(loginStatusResponseDto);
        }

        // 인증된 사용자일 경우
        Long memberId = userPrincipal.getMemberId();
        LoginStatusResponseDto loginStatusResponseDto = authService.getLoginStatus(memberId);

        return ApiResponse.success(loginStatusResponseDto);
    }

    /**
     * 사용자의 권한을 관리자일 경우 일반 회원으로, 일반 회원일 경우 관리자로 변경합니다.
     *
     * @param userPrincipal 사용자의 인증 정보
     * @return 성공 메시지
     */
    @PatchMapping("/role")
    public ApiResponse<UpdateRoleResponseDto> updateRole(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        if (profile.equals("prod")) {
            throw new InvalidProfileException(profile, "프로덕션 환경에서는 지원되지 않는 기능입니다.");
        }

        Long memberId = userPrincipal.getMemberId();

        UpdateRoleResponseDto updateRoleResponseDto = authService.updateRole(memberId);

        return ApiResponse.success(updateRoleResponseDto);
    }

}