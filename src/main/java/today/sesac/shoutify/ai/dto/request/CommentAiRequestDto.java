package today.sesac.shoutify.ai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CommentAiRequestDto extends AiRequestDto {

    private String content;
}
