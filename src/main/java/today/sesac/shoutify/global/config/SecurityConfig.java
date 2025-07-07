package today.sesac.shoutify.global.config;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import today.sesac.shoutify.auth.CustomLogoutSuccessHandler;
import today.sesac.shoutify.auth.OAuth2AuthenticationSuccessHandler;
import today.sesac.shoutify.auth.service.CustomOAuth2UserService;

/**
 * TODO: SecurityConfig의 위치 도메인 패키지 안에서 config 패키지를 만들고 넣을지, 공통 패키지를 안에 config 패키지를 만들지 논의 필요
 * 스프링 시큐리티 설정을 관리하는 클래스입니다.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
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
                        //TODO: 실패 시 handler도 추가하기
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

    /**
     * TODO: CORS 설정 시큐리티 설정 안에서 정할지, 아니면 따로 팔지 고민 필요
     * CORS 설정
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(
                Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*")); //TODO: 헤더 설정 고민하기
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
