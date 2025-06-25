package today.sesac.shoutify.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PostAiRequestDto implements AiRequestDto {

    private String title;
    private String conceptType;
    private String emotionType;
    private String content;
}
