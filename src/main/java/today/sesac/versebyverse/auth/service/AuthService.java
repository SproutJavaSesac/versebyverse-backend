package today.sesac.versebyverse.auth.service;

import jakarta.transaction.Transactional;
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
import org.springframework.web.client.RestTemplate;
import today.sesac.versebyverse.auth.exception.WithdrawFailureException;
import today.sesac.versebyverse.member.service.MemberService;

/**
 * 로그인 등 인증/인가 기능을 담당하는 서비스.
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {


    private final OAuth2AuthorizedClientService authorizedClientService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final MemberService memberService;

    /**
     * 탈퇴 요청을 수행합니다.
     *
     * @param username 사용자를 구분하기 위해 사용되는 이름입니다.
     */
    public void withdraw(Long memberId, String username) {

        // 1. 현재 세션에서 액세스 토큰 가져오기
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient("google", username);
        if (client == null) {
            throw new WithdrawFailureException("client", "OAuth2AuthorizedClient 존재하지 않음");
        }

        // access 토큰, refresh 토큰 확인하기
        // TODO: refreshToken 문제 해결하기 - 로그인 후 시간 경과되면 탈퇴 안 될 것으로 추정
        OAuth2AccessToken accessToken = client.getAccessToken();
//        OAuth2RefreshToken refreshToken = client.getRefreshToken();
//        if (refreshToken == null) {
//            throw new WithdrawFailureException("refreshToken", "refreshToken 존재하지 않음");
//        }

        // 2. 구글 연동 해제 API 호출
        revokeGoogleAccess(accessToken);

        // 3. DB에서 회원 삭제
        memberService.deleteMember(memberId);
    }

    /**
     * TODO: 카카오나 다른 소셜 로그인이 추가되면 어떻게 변경할지 고민하기
     * 회원의 구글 아이디와 서비스의 연동을 해제하는 메서드입니다.
     *
     * @param accessToken 엑세스 토큰이 필요합니다.
     */
    public void revokeGoogleAccess(OAuth2AccessToken accessToken) {
        String revokeUrl = "https://oauth2.googleapis.com/revoke";

        // 헤더 설정: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // TODO: restTemplate로 http 메세지 보내는 방식 더 알아보기
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", accessToken.getTokenValue());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(revokeUrl, request,
                    String.class);
            log.info("Google token revoked successfully: {}", response.getStatusCode());
        } catch (Exception e) { //TODO: Exception으로 묶는 게 맞는지 체크
            log.error("Failed to revoke Google token: {}", e.getMessage());
            throw new WithdrawFailureException("accessToken", "구글 연동 해제 중 문제 발생");
        }
    }


}
