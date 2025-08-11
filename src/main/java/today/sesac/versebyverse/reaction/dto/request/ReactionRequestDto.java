package today.sesac.versebyverse.reaction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 게시글, 댓글 요청 dto
 */
@Getter
@AllArgsConstructor
public class ReactionRequestDto {
    private final Emotion type;
}
