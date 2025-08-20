package today.sesac.versebyverse.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import today.sesac.versebyverse.auth.CustomLogoutSuccessHandler;
import today.sesac.versebyverse.auth.OAuth2AuthenticationSuccessHandler;
import today.sesac.versebyverse.auth.service.CustomOAuth2UserService;

/**
 * TODO: SecurityConfig의 위치 도메인 패키지 안에서 config 패키지를 만들고 넣을지, 공통 패키지를 안에 config 패키지를 만들지 논의 필요
 * 스프링 시큐리티 설정을 관리하는 클래스입니다.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Value("${client.url}")     //TODO: 방식 맞는지 다시 체크하기
    private String clientUrl;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)  // TODO: 세션의 경우 csrf 보안 대책 필요 -> 보완하기
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/oauth2/authorization",
                                "/login/oauth2/code/*",
                                "/api/**"  // TODO: 팀원 기능 구현에 방해되지 않도록 임시 설정, 추후 삭제할 것
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                                .authorizationEndpoint(oAuth2 -> oAuth2
                                        .baseUri("/api/oauth2/authorization"))
                                .redirectionEndpoint(oAuth2 -> oAuth2
                                        .baseUri("/login/oauth2/code/*"))
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig
                                                .userService(customOAuth2UserService))
                                .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler)
                ).logout(logout -> logout   // TODO: 프론트 테스트를 위한 임시 로그아웃 구현 -> 수정 필요
                        .logoutUrl("/api/v1/auth/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                )
        ;

        return http.build();
    }

}