package today.sesac.versebyverse.global.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 메시지 소스를 설정하는 클래스입니다.
 *
 * <p>기본적으로 `messages.properties` 파일을 사용하며, 로케일에 따라 다른 메시지를 제공합니다.
 * 로케일 해상도는 Accept-Language 헤더를 사용하여 클라이언트의 언어 설정에 따라 자동으로 결정됩니다.</p>
 */
@Configuration
public class MessageConfig {

    /**
     * 메시지 소스를 빈으로 등록합니다.
     *
     * <p>프로퍼티 파일을 통해 국제화된 메시지를 제공합니다.</p>
     *
     * @return ReloadableResourceBundleMessageSource
     */
    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();

        // messages 프로퍼티 파일 기본명 지정 (messages.properties, messages_ko.properties 자동 찾음)
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

        // 메시지 키가 없으면 코드명을 그대로 반환
        messageSource.setUseCodeAsDefaultMessage(false); // false: 없는 키를 보내면 예외 발생
        messageSource.setFallbackToSystemLocale(
                true); // 시스템 로케일을 기본으로 사용함. messages.properties 파일이 없으면 시스템 로케일에 따라 messages_ko.properties 등을 찾음
        return messageSource;
    }
}