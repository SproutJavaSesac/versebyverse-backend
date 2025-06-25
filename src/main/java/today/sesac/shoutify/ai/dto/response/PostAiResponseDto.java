package today.sesac.shoutify.ai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PostAiResponseDto implements AiResponseDto {

    private String title;
    private String conceptType;
    private String emotionType;
    private String content;
}