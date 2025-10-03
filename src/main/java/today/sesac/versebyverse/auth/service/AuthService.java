package today.sesac.versebyverse.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.auth.dto.response.LoginStatusResponseDto;
import today.sesac.versebyverse.auth.dto.response.UpdateRoleResponseDto;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.RoleType;
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

    /**
     * 사용자의 인증 여부 및 인증 상태를 확인할 때 필요한 정보를 반환합니다.
     *
     * @param memberId 사용자의 ID
     * @return 로그인 상태 반환 DTO
     */
    public LoginStatusResponseDto getLoginStatus(Long memberId) {

        Member member = memberService.findById(memberId);
        String nickname = member.getNickname();
        String email = member.getEmail();
        RoleType roleType = member.getRoleType();

        return LoginStatusResponseDto.createAuthenticated(memberId, nickname, email, roleType);
    }

    /**
     * 사용자의 권한을 변경합니다.
     *
     * @param memberId 사용자의 ID
     */
    @Transactional
    public UpdateRoleResponseDto updateRole(Long memberId) {

        Member member = memberService.findById(memberId);

        RoleType roleType = member.getRoleType();
        if (roleType == RoleType.ROLE_ADMIN) {
            member.updateRoleType(RoleType.ROLE_USER);
        } else if (roleType == RoleType.ROLE_USER) {
            member.updateRoleType(RoleType.ROLE_ADMIN);
        }

        changeAuthenticationAuthority(member);

        return UpdateRoleResponseDto.of(member.getRoleType());
    }

    private void changeAuthenticationAuthority(Member member) {

        OAuth2AuthenticationToken authentication =
                (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String authorizedClientRegistrationId = authentication.getAuthorizedClientRegistrationId();
        UserPrincipal newPrincipal =
                UserPrincipal.create(member.getId(), member.getRoleType(), member.getSocialType(),
                        member.getEmail());
        OAuth2AuthenticationToken newAuthentication =
                new OAuth2AuthenticationToken(newPrincipal, newPrincipal.getAuthorities(),
                        authorizedClientRegistrationId);

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

}
