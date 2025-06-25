package today.sesac.shoutify.auth.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;

/**
 * 소셜 로그인을 할 때 사용자의 인증 정보를 저장하는 클래스입니다. memberId 등 추가적인 정보를 꺼내 쓸 수 있도록 커스텀했습니다.
 * TODO: 일반 로그인 구현 시 userDetails를 동시에 구현할지 등 인증 정보를 일괄적으로 관리할 방법 고민하기
 * TODO: 패키지 위치 고민 필요
 * TODO: 필드 값 종류와 메서드 고민하기. 스프링 시큐리티의 목적에 맞도록 설계할 것
 */
@Getter
public class UserPrincipal implements OAuth2User {

    private final Long memberId;
    private final RoleType roleType;
    private final SocialType socialType;
    private final String email;

    private UserPrincipal(Long memberId, RoleType roleType, SocialType socialType, String email) {
        this.memberId = memberId;
        this.roleType = roleType;
        this.socialType = socialType;
        this.email = email;
    }

    public static UserPrincipal create(Long memberId, RoleType roleType, SocialType socialType,
            String username) {
        return new UserPrincipal(memberId, roleType, socialType, username);
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    /**
     * TODO: 권한 추가하기
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return this.email;
    }
}
