package today.sesac.shoutify.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

}
