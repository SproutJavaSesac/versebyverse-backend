package today.sesac.shoutify.ai.prompt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PromptTemplateLoaderTest {

    @Autowired
    private PromptTemplateLoader loader;

    @Test
    void 템플릿_정상_로딩_및_파싱() {
        // 임의의 PromptType으로 테스트
        PromptType type = PromptType.EMOTION_ANALYSIS; // 실제 enum 값 중 하나
        PromptTemplate template = loader.getTemplate(type);

        assertThat(template).isNotNull();
        assertThat(template.getRole()).isNotBlank();
        assertThat(template.getCondition()).isNotBlank();
        assertThat(template.getExample()).isNotBlank();
    }
}