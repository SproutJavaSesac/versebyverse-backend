package today.sesac.versebyverse.auth.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.RoleType;
import today.sesac.versebyverse.member.entity.SocialType;
import today.sesac.versebyverse.member.exception.MemberNotFoundException;
import today.sesac.versebyverse.member.service.MemberService;

/**
 * 소셜 로그인 과정에서 리소스 서버로부터 사용자 정보를 받을 때, 사용자 정보를 가공할 수 있도록 설정하는 클래스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 소셜 로그인 타입을 가져옵니다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = extractSocialType(registrationId);

        // 사용자 정보를 가져옵니다.
        String email = extractEmail(oAuth2User, socialType);
        String nickname = extractNickname(oAuth2User, socialType);

        // 사용자 역할을 부여합니다.
        RoleType roleType = RoleType.ROLE_USER;

        // 사용자 정보가 없으면 db에 저장, 있으면 불러오기
        // TODO: memberService 관련 기능 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 수정하기
        Member member;
        try {
            member = memberService.findByEmailAndSocialType(email, socialType);
            log.info(
                    "findByEmailAndSocialType: roleType = {}, socialType = {}, email = {}, nickname = {}",
                    member.getRoleType(), member.getSocialType(), member.getEmail(),
                    member.getNickname()); // TODO: 프론트 화면 구현 이후 로그 지우기
        } catch (MemberNotFoundException e) {
            member = memberService.createMember(roleType, socialType, email, nickname);
            log.info("createMember: roleType = {}, socialType = {}, email = {}, nickname = {}",
                    roleType, socialType, email, nickname);
        }

        return UserPrincipal.create(member.getId(), roleType, socialType,
                email);
    }

    private SocialType extractSocialType (String registrationId) {
        if (registrationId.equals("google")) {
            return SocialType.GOOGLE;
        } else if (registrationId.equals("kakao")) {
            return SocialType.KAKAO;
        } else {
            throw new OAuth2AuthenticationException(
                    "Unsupported OAuth2 provider: " + registrationId);
        }
    }

    private String extractNickname (OAuth2User oAuth2User, SocialType socialType) {
        if (socialType.equals(SocialType.GOOGLE)) {
            return oAuth2User.getAttribute("name");
        } else if (socialType.equals(SocialType.KAKAO)) {
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>)kakaoAccount.get("profile");
            return (String) profile.get("nickname");
        } else {
            throw new OAuth2AuthenticationException(
                    "Unsupported OAuth2 provider: " + socialType);
        }
    }

    private String extractEmail (OAuth2User oAuth2User, SocialType socialType) {
        String email = null;
        if (socialType.equals(SocialType.GOOGLE)) {
            email = oAuth2User.getAttribute("email");
        } else if (socialType.equals(SocialType.KAKAO)) {
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            email = (String) kakaoAccount.get("email");
        }
        return email;
    }
}