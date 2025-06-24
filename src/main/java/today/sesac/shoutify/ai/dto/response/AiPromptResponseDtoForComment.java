package today.sesac.shoutify.ai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
class AiPromptResponseDtoForComment implements AiResponseDto {

    private String content;
}
