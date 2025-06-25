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
 * <p>
 * PromptTemplateLoader는 템플릿 파일을 읽어 각 {@link PromptType} 별로 {@link PromptTemplate} 객체로 변환하고, 이를 매핑하여
 * 제공하는 클래스입니다.
 * </p>
 * <ul>
 *   <li>템플릿 파일은 resources/templates/ 디렉토리에 [프롬프트타입].md 형식으로 존재해야 합니다.</li>
 *   <li>파일 내용은 "---" 구분자를 기준으로 role, condition, example 영역으로 분리되어야 합니다.</li>
 * </ul>
 *
 * <pre>
 * [role]
 * ---
 * [condition]
 * ---
 * [example]
 * </pre>
 */
@Component
public class PromptTemplateLoader {

    private final Map<PromptType, PromptTemplate> templateMap = new EnumMap<>(PromptType.class);

    /**
     * 생성자.<br> 클래스패스 내 "templates/[promptType].md" 파일을 읽어들여 각 타입별 템플릿을 매핑합니다.<br> 파일 포맷이 잘못된 경우 또는
     * 파일을 찾을 수 없을 경우 RuntimeException이 발생합니다.
     */
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
