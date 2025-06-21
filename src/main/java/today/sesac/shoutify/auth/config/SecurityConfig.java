package today.sesac.shoutify.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import today.sesac.shoutify.auth.OAuth2AuthenticationSuccessHandler;
import today.sesac.shoutify.auth.service.OAuth2UserCustomService;

/**
 * TODO: SecurityConfig의 위치 도메인 패키지 안에서 config 패키지를 만들고 넣을지, 공통 패키지를 안에 config 패키지를 만들지 논의 필요
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	private final OAuth2UserCustomService oAuth2UserCustomService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(
					"/api/oauth2/authorization",
					"/login/oauth2/code/*"
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
						.userService(oAuth2UserCustomService))
				.successHandler(oAuth2AuthenticationSuccessHandler)
			)
		;

		return http.build();
	}

	/**
	 * TODO: CORS 설정 시큐리티 설정 안에서 정할지, 아니면 따로 팔지 고민 필요
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(List.of("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
