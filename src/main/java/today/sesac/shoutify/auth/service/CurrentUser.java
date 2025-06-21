package today.sesac.shoutify.auth.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;

@Getter
public class CurrentUser implements OAuth2User {

	private final Long memberId;
	private final RoleType roleType;
	private final SocialType socialType;
	private final String username;

	@Override
	public <A> A getAttribute(String name) {
		return OAuth2User.super.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return Map.of();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getName() {
		return this.username;
	}

	CurrentUser(Long memberId, RoleType roleType, SocialType socialType, String username) {
		this.memberId = memberId;
		this.roleType = roleType;
		this.socialType = socialType;
		this.username = username;
	}

	public static CurrentUser create(Long memberId, RoleType roleType, SocialType socialType, String username) {
		return new CurrentUser(memberId, roleType, socialType, username);
	}
}
