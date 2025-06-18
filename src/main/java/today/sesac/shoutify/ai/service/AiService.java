package today.sesac.shoutify.ai.service;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.ai.dto.request.CensorRequest;

/**
 * AI 욕설 문장 변환, 감정 분석 요청할 떄 사용됩니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public String censorText(CensorRequest censorRequest) {
        log.info("ChatClient: {}", chatClient.getClass().getSimpleName());
        String title = censorRequest.getTitle();
        String beforeText = censorRequest.getBeforeText();

        // 더 구체적인 프롬프트 작성
        String promptText = String.format(
                "다음 텍스트에서 욕설이나 부적절한 표현을 순화시켜주세요. 원문: '%s'",
                beforeText != null ? beforeText : title
        );

        Prompt prompt = new Prompt(promptText);
        String result = chatClient.prompt(prompt).call().content();

        log.info("변환 결과: {}", result);
        return result;
    }

    @PostConstruct
    public void testGrpcLoad() {

        System.out.println("=== gRPC 클래스 존재 여부 체크 ===");
        try {
            Class.forName("io.grpc.ManagedChannel");
            System.out.println("✅ grpc-core 있음");
            Class.forName("io.grpc.okhttp.OkHttpChannelProvider");
            System.out.println("✅ grpc-okhttp 있음");
            Class.forName("io.grpc.netty.shaded.io.grpc.netty.NettyChannelProvider");
            System.out.println("✅ grpc-netty-shaded 있음");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ 없음: " + e.getMessage());
        }

        try {
            Class<?> grpcProvider = Class.forName("io.grpc.okhttp.OkHttpChannelProvider");
            System.out.println("✅ gRPC OKHttpProvider found: " + grpcProvider.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("❌ gRPC OkHttpChannelProvider NOT FOUND!");
        }
    }

    public String askGemini(String message) {
        Prompt prompt = new Prompt(message);
        return chatClient.prompt(prompt).call().content();
    }
}
