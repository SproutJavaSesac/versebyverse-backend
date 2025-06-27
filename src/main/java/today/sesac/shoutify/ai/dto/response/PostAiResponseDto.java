package today.sesac.shoutify.ai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class PostAiResponseDto extends AiResponseDto {

    private String title;
    private String conceptType;
    private String emotionType;
    private String content;
}