package today.sesac.shoutify.ai.prompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * PromptTemplateLoader는 템플릿 파일을 읽어 각 {@link PromptType} 별로 {@link PromptTemplate} 객체로 변환하고, 이를 매핑하여
 * 제공하는 클래스입니다.
 *
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

    /**
     * 템플릿 파일이 위치한 디렉토리 상대 경로.
     */
    private static final String TEMPLATE_PATH = "templates/";

    /**
     * PromptType별 PromptTemplate 매핑 정보.
     */
    private final Map<PromptType, PromptTemplate> templateMap = new EnumMap<>(PromptType.class);

    /**
     * 생성자.<br> 모든 {@link PromptType}에 대해 해당 템플릿 파일을 로딩하여 {@link PromptTemplate}로 파싱 및 매핑합니다.
     */
    public PromptTemplateLoader() {
        for (PromptType type : PromptType.values()) {
            templateMap.put(type, loadTemplate(type));
        }
    }

    /**
     * 주어진 {@link PromptType}에 해당하는 템플릿 파일을 읽어 {@link PromptTemplate}로 변환합니다.
     *
     * <p>파일이 없거나 포맷이 잘못된 경우 RuntimeException 또는 IllegalArgumentException이 발생합니다.</p>
     *
     * @param type 프롬프트 타입
     * @return 파싱된 {@link PromptTemplate} 인스턴스
     * TODO : 로직 메서드로 분리하기
     */
    private PromptTemplate loadTemplate(PromptType type) {
        String filePath = TEMPLATE_PATH + type.name().toLowerCase() + ".md";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource(filePath).getInputStream(),
                        StandardCharsets.UTF_8))) {
            String content = reader.lines().collect(Collectors.joining("\n"));
            String[] parts = content.split("---", 3);

            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid format in: " + filePath);
            }

            return PromptTemplate.of(parts[0].trim(), // role
                    parts[1].trim(), // condition
                    parts[2].trim()  // example
            );
        } catch (IOException e) {
            throw new RuntimeException("[PromptTemplateLoader] 템플릿 파일 로드 실패:" + filePath, e);
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
