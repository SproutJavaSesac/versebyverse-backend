package today.sesac.shoutify.ai.prompt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * 템플릿 파일을 읽어 PromptTemplate 객체로 변환하고, PromptType별로 매핑하여 제공하는 로더 클래스입니다.
 */
@Component
public class PromptTemplateLoader {

    private final Map<PromptType, PromptTemplate> templateMap = new EnumMap<>(PromptType.class);

    public PromptTemplateLoader() {
        for (PromptType type : PromptType.values()) {
            try {
                String path = "templates/%s.md".formatted(type.name().toLowerCase());
                ClassPathResource resource = new ClassPathResource(path);

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                    String content = reader.lines().collect(Collectors.joining("\n"));
                    String[] parts = content.split("---", 3);

                    if (parts.length < 3) {
                        throw new IllegalArgumentException("Invalid format in: " + path);
                    }

                    PromptTemplate template = PromptTemplate.of(parts[0].trim(), // role
                            parts[1].trim(), // condition
                            parts[2].trim()  // example
                    );
                    templateMap.put(type, template);
                }

            } catch (Exception e) {
                throw new RuntimeException("Failed to load template for " + type, e);
            }
        }
    }

    /**
     * 주어진 PromptType에 해당하는 템플릿을 반환합니다.
     *
     * @param type 프롬프트 타입
     * @return PromptTemplate 객체
     */
    public PromptTemplate getTemplate(PromptType type) {
        return templateMap.get(type);
    }
}
