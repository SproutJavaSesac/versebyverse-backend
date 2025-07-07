package today.sesac.shoutify.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;
import today.sesac.shoutify.member.exception.MemberNotFoundException;
import today.sesac.shoutify.member.service.MemberService;

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
        log.info("loadUser.registrationId: {}", registrationId); // ex.google
        SocialType socialType = null;
        if (registrationId.equals("google")) {
            socialType = SocialType.GOOGLE;
        }
        log.info("socialType: {}", socialType);

        // 사용자 정보를 가져옵니다.
        String email = oAuth2User.getAttribute("email");
        log.info("email: {}", email);
        String nickname = oAuth2User.getAttribute("name");
        log.info("nickname: {}", nickname); // TODO: 현재는 프로필의 이름을 그대로 받는 중, 변경 필요

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

        UserPrincipal userPrincipal = UserPrincipal.create(member.getId(), roleType, socialType,
                email);

        // TODO: 프론트 테스트를 위해 id 바꿔치기 - 다른 api 연동 완료되면 지울 것.
        UserPrincipal mockUserPrincipal = UserPrincipal.create(1L, RoleType.ROLE_USER,
                SocialType.GOOGLE, memberService.getMember(1L).getEmail());
        
        return mockUserPrincipal;
    }
}
