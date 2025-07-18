package today.sesac.versebyverse.global.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 테스트에 사용되는 스프링 시큐리티 설정을 관리하는 클래스입니다 테스트에는 기존에 설정한 스프링 시큐리티 설정이 반영되지 않기 때문에, 별도의 클래스를 작성합니다.
 */
@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain testFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // post 요청에 발생하는 csrf 요청
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // TODO:
                );

        return http.build();
    }
}