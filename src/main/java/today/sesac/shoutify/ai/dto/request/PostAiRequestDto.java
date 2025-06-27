package today.sesac.shoutify.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class PostAiRequestDto extends AiRequestDto {

    private String title;
    private String conceptType;
    private String emotionType;
    private String content;
}
