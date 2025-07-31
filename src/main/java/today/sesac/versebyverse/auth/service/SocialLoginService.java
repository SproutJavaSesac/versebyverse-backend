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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import today.sesac.versebyverse.member.exception.WithdrawFailureException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocialLoginService {

    private final OAuth2AuthorizedClientService authorizedClientService;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 소셜 로그인 플랫폼과의 연동 해제를 구현하는 메서드입니다.
     *
     * @param username 사용자를 식별할 수 있는 이름.
     */
    public void revokeAccess(String username) {
        OAuth2AccessToken accessToken = getAccessToken(username);
        sendRevoke(accessToken);
    }

    /**
     * 사용자의 소셜 로그인 엑세스 토큰을 조회합니다.
     * TODO: refreshToken 문제 해결하기 - 로그인 후 시간 경과되면 탈퇴 안 될 것으로 추정
     * @param username 사용자를 식별할 수 있는 이름
     * @return OAuth2AccessToken 엑세스 토큰
     */
    private OAuth2AccessToken getAccessToken(String username) {

        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient("google", username);
        if (client == null) {
            throw new WithdrawFailureException("client", "OAuth2AuthorizedClient가 존재하지 않습니다.");
        }

        return client.getAccessToken();
    }

    /**
     * TODO: 카카오나 다른 소셜 로그인이 추가되면 어떻게 변경할지 고민하기
     * 구글 api를 호출하여 엑세스 토큰을 해제하는 메서드입니다.
     *
     * @param accessToken 엑세스 토큰
     */
    private void sendRevoke(OAuth2AccessToken accessToken) {

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
