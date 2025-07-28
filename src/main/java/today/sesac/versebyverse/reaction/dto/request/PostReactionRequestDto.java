package today.sesac.versebyverse.reaction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Emotion;

@Getter
@AllArgsConstructor
public class PostReactionRequestDto {
    private final Emotion type;
}
