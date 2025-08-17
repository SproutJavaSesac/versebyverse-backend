package today.sesac.versebyverse.reaction.dto.request;

import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 게시글, 댓글 요청 dto.
 */
public record ReactionRequestDto(
        Emotion type
) {

}
