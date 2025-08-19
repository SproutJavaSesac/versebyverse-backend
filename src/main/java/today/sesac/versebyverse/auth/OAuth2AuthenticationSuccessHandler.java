package today.sesac.versebyverse.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.member.entity.RoleType;
import today.sesac.versebyverse.member.entity.SocialType;

/**
 * 소셜 로그인이 성공했을 떄 마지막으로 작동하는 동작들을 설정하는 클래스입니다.
 * //TODO: handler 패키지로 이동하기
 */
@Slf4j
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${client.url}")
    private String clientUrl;

    /**
     * 로그인 성공 시 홈(/) 페이지로 리다이렉트.
     * TODO: 원래 로그인하려던 페이지로 보내주기(프론트 구현 필요)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        //TODO: 팀원 확인용 로그 - 테스트코드 직성 이후 삭제하기, 필요한 내용은 debug로 조정하기
        boolean authenticated = authentication.isAuthenticated();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long memberId = userPrincipal.getMemberId();
        RoleType roleType = userPrincipal.getRoleType();
        SocialType socialType = userPrincipal.getSocialType();
        String email = userPrincipal.getEmail();
        log.info("authenticated = {}", authenticated);
        log.info("memberId = {}, roleType = {}, socialType = {}, email = {}",
                memberId, roleType, socialType, email);
        log.info("-----authentication success!!-----");

        String redirectUrl = clientUrl + "/auth/callback" + "?status=success";

        //TODO: 소설 로그인 이후 새로 아이디 생성되었으면 201 반환, 아닐 경우 200 반환 구현하기
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, redirectUrl);

    }
}