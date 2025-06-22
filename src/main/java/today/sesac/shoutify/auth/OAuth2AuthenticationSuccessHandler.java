package today.sesac.shoutify.auth;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import today.sesac.shoutify.auth.service.CurrentUser;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;

/**
 * 소셜 로그인이 성공했을 떄 마지막으로 작동하는 동작들을 설정하는 클래스입니다.
 */
@Slf4j
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/**
	 * 로그인 성공 시 홈(/) 페이지로 리다이렉트
	 * TODO: 원래 로그인하려던 페이지로 보내주기(프론트 구현 필요)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

		//TODO: 팀원 확인용 로그 - 테스트코드 직성 이후 삭제하기
		boolean authenticated = authentication.isAuthenticated();
		CurrentUser currentUser = (CurrentUser)authentication.getPrincipal();
		Long memberId = currentUser.getMemberId();
		RoleType roleType = currentUser.getRoleType();
		SocialType socialType = currentUser.getSocialType();
		String username = currentUser.getUsername();
		log.info("authenticated = {}", authenticated);
		log.info("memberId = {}, roleType = {}, socialType = {}, username = {}",
			memberId, roleType, socialType, username);
		log.info("-----authentication success!!-----");

		//TODO: 소설 로그인 이후 새로 아이디 생성되었으면 201 반환, 아닐 경우 200 반환 구현하기
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, "http://localhost:3000");

	}
}
