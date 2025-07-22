package today.sesac.versebyverse.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import today.sesac.versebyverse.auth.exception.WithdrawFailureException;
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

    private final OAuth2AuthorizedClientService authorizedClientService;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 탈퇴 요청을 수행합니다.
     *
     * @param username 사용자를 구분하기 위해 사용되는 이름입니다.
     */
    @Transactional
    public void withdraw(Long memberId, String username) {
        log.info("회원 탈퇴 시작, memberId: {}, username: {}", memberId, username);

        // 1. 현재 세션에서 액세스 토큰 가져오기
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient("google", username);
        if (client == null) {
            throw new WithdrawFailureException("client", "OAuth2AuthorizedClient가 존재하지 않습니다.");
        }

        // TODO: refreshToken 문제 해결하기 - 로그인 후 시간 경과되면 탈퇴 안 될 것으로 추정
        OAuth2AccessToken accessToken = client.getAccessToken();

        // 2. 구글 연동 해제 API 호출
        revokeGoogleAccess(accessToken);

        // 3. DB에서 회원 삭제
        memberService.deleteMember(memberId);

        log.info("회원 탈퇴 완료, memberId: {}, username: {}", memberId, username);
    }

    /**
     * TODO: 카카오나 다른 소셜 로그인이 추가되면 어떻게 변경할지 고민하기
     * 회원의 구글 아이디와 서비스의 연동을 해제하는 메서드입니다.
     *
     * @param accessToken 엑세스 토큰이 필요합니다.
     */
    private void revokeGoogleAccess(OAuth2AccessToken accessToken) {
        String revokeUrl = "https://oauth2.googleapis.com/revoke";

        // http 요청을 위한 RestTemplate 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", accessToken.getTokenValue());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // 소셜 로그인 해제 api 요청 보내기
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(revokeUrl, request,
                    String.class);
            log.info("소셜 로그인 연동 해제에 성공했습니다: {}", response.getStatusCode());
        } catch (HttpClientErrorException e) {
            throw new WithdrawFailureException("clientError", "소셜 로그인 연동 해제 중 문제 발생");
        } catch (Exception e) {
            throw new WithdrawFailureException("serverError", "일시적 서버 문제");
        }
    }


}
