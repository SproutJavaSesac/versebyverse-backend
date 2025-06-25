package today.sesac.shoutify.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CommentAiRequestDto implements AiRequestDto {

    private String content;
}
