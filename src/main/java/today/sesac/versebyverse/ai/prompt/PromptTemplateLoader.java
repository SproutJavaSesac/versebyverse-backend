package today.sesac.versebyverse.ai.prompt;

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
 * </ul>
 *
 * <pre>
 * ## role
 *
 * ## condition
 *
 * ## example
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
     */
    private PromptTemplate loadTemplate(PromptType type) {

        String filePath = buildFilePath(type);
        String content = readTemplateFile(filePath);
        return parseTemplateContent(content, filePath);
    }

    /**
     * PromptType을 기반으로 템플릿 파일 경로를 생성합니다.
     *
     * @param type 프롬프트 타입
     * @return 템플릿 파일 경로
     */
    private String buildFilePath(PromptType type) {

        return TEMPLATE_PATH + type.name().toLowerCase() + ".md";
    }

    /**
     * 템플릿 파일을 읽어 문자열로 반환합니다.
     *
     * @param filePath 읽을 파일 경로
     * @return 파일 내용
     * @throws RuntimeException 파일 읽기 실패 시
     */
    private String readTemplateFile(String filePath) {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource(filePath).getInputStream(),
                        StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalArgumentException("[PromptTemplateLoader] 템플릿 파일 로드 실패: " + filePath,
                    e);
        }
    }

    /**
     * 템플릿 파일 내용을 파싱하여 PromptTemplate 객체를 생성합니다.
     *
     * @param content  템플릿 파일 내용
     * @param filePath 파일 경로 (에러 메시지용)
     * @return 파싱된 PromptTemplate 객체
     * @throws IllegalArgumentException 템플릿 형식이 올바르지 않은 경우
     */
    private PromptTemplate parseTemplateContent(String content, String filePath) {

        String[] sections = extractSections(content, filePath);
        String role = extractSectionContent(sections[1], "role");
        String condition = extractSectionContent(sections[2], "condition");
        String example = extractSectionContent(sections[3], "example");

        return PromptTemplate.of(role, condition, example);
    }

    /**
     * 템플릿 내용을 ## 기준으로 섹션별로 분리합니다.
     *
     * @param content  템플릿 파일 내용
     * @param filePath 파일 경로 (에러 메시지용)
     * @return 분리된 섹션 배열
     * @throws IllegalArgumentException 섹션이 부족한 경우
     */
    private String[] extractSections(String content, String filePath) {

        String[] sections = content.split("##");

        if (sections.length < 4) { // 첫 번째는 빈 문자열, 나머지 3개가 role, condition, example
            throw new IllegalArgumentException(
                    String.format("템플릿 파일 형식이 올바르지 않습니다. role, condition, example 섹션이 모두 필요합니다: %s",
                            filePath));
        }

        return sections;
    }

    /**
     * 섹션 내용에서 헤더를 제거하고 실제 내용만 추출합니다.
     *
     * @param section     섹션 원본 내용
     * @param sectionName 섹션 이름 (에러 메시지용)
     * @return 정제된 섹션 내용
     */
    private String extractSectionContent(String section, String sectionName) {

        if (section == null || section.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("%s 섹션의 내용이 비어있습니다.", sectionName));
        }

        // 섹션 헤더 제거 (예: "role" 텍스트 제거)
        String content = section.replaceFirst("^\\s*" + sectionName + "\\s*", "").trim();

        if (content.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("%s 섹션의 내용이 헤더만 있고 실제 내용이 없습니다.", sectionName));
        }

        return content;
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