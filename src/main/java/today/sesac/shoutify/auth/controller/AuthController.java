package today.sesac.shoutify.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import today.sesac.shoutify.auth.service.CurrentUser;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	// TODO: 로그인 상태 확인용 임시 메서드 - 삭제 예정
	@GetMapping("/status")
	public ApiResponse<?> checkAuthStatus(@AuthenticationPrincipal CurrentUser currentUser) {
		Long memberId = currentUser.getMemberId();
		RoleType roleType = currentUser.getRoleType();
		SocialType socialType = currentUser.getSocialType();
		String username = currentUser.getUsername();
		log.info("checkAuthStatus: memberId = {}, roleType = {}, socialType = {}, username = {}",
			memberId, roleType, socialType, username);

		CheckStatusResponse response = new CheckStatusResponse(memberId, roleType, socialType, username);

		return ApiResponse.success(response);
	}
}
