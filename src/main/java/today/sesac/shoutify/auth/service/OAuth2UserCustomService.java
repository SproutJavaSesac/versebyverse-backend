package today.sesac.shoutify.auth.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;
import today.sesac.shoutify.member.exception.MemberNotFoundException;
import today.sesac.shoutify.member.service.MemberService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

	private final MemberService memberService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		log.info("loadUser.registrationId: {}", registrationId);
		SocialType socialType = null;
		if (registrationId.equals("google")) {
			socialType = SocialType.GOOGLE;
		}

		// TODO: oauth2User 형식에 맞게 변환
		String email = oAuth2User.getAttribute("email");

		RoleType roleType = RoleType.ROLE_USER;

		// TODO: 확인하고 없으면 새로 만들기
		Member member;
		try {
			member = memberService.findByUsername(email);
		} catch (MemberNotFoundException e) {
			member = memberService.createMember(roleType, socialType, "닉네임");
		}

		return CurrentUser.create(member.getId(), roleType, socialType,
			email);
	}
}
