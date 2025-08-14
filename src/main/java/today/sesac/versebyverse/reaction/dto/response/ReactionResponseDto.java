package today.sesac.versebyverse.reaction.dto.response;

import java.util.Map;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 게시글, 댓글 응답 record.
 */
public record ReactionResponseDto(
        //추가 or 변경 or 삭제된 감정
        Emotion type,

        //반응 총 갯수
        int recationTotalCount,

        //반응별 갯수
        Map<Emotion, String> reactionDetails
) {

    /**
     * of 정적 팩토리 메서드.
     */
    public ReactionResponseDto of(Emotion type, int recationTotalCount,
            Map<Emotion, String> reactionDetails) {

        return new ReactionResponseDto(type, recationTotalCount, reactionDetails);
    }
}
