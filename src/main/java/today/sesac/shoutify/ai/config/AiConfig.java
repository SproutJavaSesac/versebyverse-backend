package today.sesac.shoutify.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ai 설정 클래스입니다.
 */
@Configuration
public class AiConfig {

    /**
     * ChatClient Bean 주입
     *
     * @param chatClientBuilder chatClientBuilder
     * @return ChatClient chatClient
     */
    @Bean
    public ChatClient chatClient(Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

}
