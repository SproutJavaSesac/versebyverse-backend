package today.sesac.shoutify.ai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
class CommentAiResponseDto extends AiResponseDto {

    private String content;
}
