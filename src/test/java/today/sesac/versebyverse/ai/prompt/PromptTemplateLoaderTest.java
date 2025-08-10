package today.sesac.versebyverse.ai.prompt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PromptTemplateLoaderTest {

    @Autowired
    private PromptTemplateLoader loader;

    @Test
    @DisplayName("[Ai]-프롬프트템플릿 로드 테스트")
    void getTemplate_test() {
        // 임의의 PromptType으로 테스트
//        PromptType type = PromptType.EMOTION_ANALYSIS; // 실제 enum 값 중 하나
//        PromptTemplate template = loader.getTemplate(type);
//
//        assertThat(template).isNotNull();
//        assertThat(template.getRole()).isNotBlank();
//        assertThat(template.getCondition()).isNotBlank();
//        assertThat(template.getExample()).isNotBlank();
    }
}