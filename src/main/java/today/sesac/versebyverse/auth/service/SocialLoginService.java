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
import today.sesac.versebyverse.member.entity.SocialType;
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
     * @param email 스프링 시큐리티에서 사용자를 식별할 때 사용하는 email
     */
    public void revokeAccess(String email, SocialType socialType) {
        OAuth2AccessToken accessToken = getAccessToken(email, socialType);
        sendRevoke(accessToken, socialType);
    }

    /**
     * 사용자의 소셜 로그인 엑세스 토큰을 조회합니다.
     * TODO: refreshToken 문제 해결하기 - 로그인 후 시간 경과되면 탈퇴 안 될 것으로 추정
     *
     * @param email 스프링 시큐리티에서 사용자를 식별할 때 사용하는 email
     * @return OAuth2AccessToken 엑세스 토큰
     */
    private OAuth2AccessToken getAccessToken(String email, SocialType socialType) {

        OAuth2AuthorizedClient client = null;
        if (socialType.equals(SocialType.GOOGLE)) {
            client = authorizedClientService.loadAuthorizedClient("google", email);
        } else if (socialType.equals(SocialType.KAKAO)) {
            client = authorizedClientService.loadAuthorizedClient("kakao", email);
        }

        if (client == null) {
            throw new WithdrawFailureException("client", "OAuth2AuthorizedClient가 존재하지 않습니다.");
        }

        return client.getAccessToken();
    }

    /**
     * 구글 api를 호출하여 엑세스 토큰을 해제하는 메서드입니다.
     *
     * @param accessToken 엑세스 토큰
     */
    private void sendRevoke(OAuth2AccessToken accessToken, SocialType socialType) {
        if (socialType.equals(SocialType.GOOGLE)) {
            sendRevokeGoogle(accessToken);
        } else if (socialType.equals(SocialType.KAKAO)) {
            sendRevokeKakao(accessToken);
        }
    }

    private void sendRevokeGoogle(OAuth2AccessToken accessToken) {

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
        }
    }

    private void sendRevokeKakao(OAuth2AccessToken accessToken) {

        String unlinkUrl = "https://kapi.kakao.com/v1/user/unlink";

        // http 요청을 위한 RestTemplate 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken.getTokenValue());

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(unlinkUrl, request, String.class);
            log.info("소셜 로그인 연동 해제에 성공했습니다: {}", response.getStatusCode());
        } catch (HttpClientErrorException e) {
            throw new WithdrawFailureException("clientError", "소셜 로그인 연동 해제 중 문제 발생");
        }
    }
}
