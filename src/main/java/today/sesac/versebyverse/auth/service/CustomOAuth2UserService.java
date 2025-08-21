package today.sesac.versebyverse.auth.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.RoleType;
import today.sesac.versebyverse.member.entity.SocialType;
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

        // 사용자의 정보를 가져옵니다.
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 소셜 로그인 타입을 가져옵니다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = extractSocialType(registrationId);

        // 사용자 정보를 가져옵니다.
        String email = extractEmail(oAuth2User, socialType);
        if (email == null) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), "이메일 정보가 존재하지 않습니다.", null
            );
        }

        String nickname = extractNickname(oAuth2User, socialType);
        if (nickname == null) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), "닉네임 정보가 존재하지 않습니다.", null
            );
        }

        // 사용자 역할을 부여합니다.
        RoleType roleType = RoleType.ROLE_USER;

        // 사용자 정보가 데이터베이스에 있으면 불러오고, 없으면 회원가입을 진행합니다.
        Member member = memberService.findOrCreateSocialMember(email, nickname,
                roleType, socialType);

        return UserPrincipal.create(member.getId(), member.getRoleType(), member.getSocialType(),
                member.getEmail());
    }

    private SocialType extractSocialType (String registrationId) {
        if (registrationId.equals("google")) {
            return SocialType.GOOGLE;
        } else if (registrationId.equals("kakao")) {
            return SocialType.KAKAO;
        } else {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), "지원하지 않는 소셜 로그인 제공자입니다.",
                    null
            );
        }
    }

    private String extractNickname (OAuth2User oAuth2User, SocialType socialType) {
        String nickname = null;
        if (socialType.equals(SocialType.GOOGLE)) {
            nickname = oAuth2User.getAttribute("name");
        } else if (socialType.equals(SocialType.KAKAO)) {
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            if (kakaoAccount == null) {
                return null;
            }

            Map<String, Object> profile = (Map<String, Object>)kakaoAccount.get("profile");
            if (profile == null) {
                return null;
            }
            nickname = (String) profile.get("nickname");
        }
        return nickname;
    }

    private String extractEmail (OAuth2User oAuth2User, SocialType socialType) {
        String email = null;
        if (socialType.equals(SocialType.GOOGLE)) {
            email = oAuth2User.getAttribute("email");
        } else if (socialType.equals(SocialType.KAKAO)) {
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            if (kakaoAccount == null) {
                return null;
            }
            email = (String) kakaoAccount.get("email");
        }
        return email;
    }
}