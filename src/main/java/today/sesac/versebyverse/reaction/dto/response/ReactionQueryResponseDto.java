package today.sesac.versebyverse.reaction.dto.response;

import java.util.Map;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 반응하기 조회를 위한 record.
 *
 * @param type               내가 표시한 감정 없으면 null
 * @param recationTotalCount 반응 총 갯수
 * @param reactionDetails    반응별 갯수
 */
public record ReactionQueryResponseDto(
        Emotion type,

        int recationTotalCount,

        Map<Emotion, String> reactionDetails
) {

    /**
     * 정적 팩토리 메서드.
     */
    public ReactionQueryResponseDto of(Emotion type, int recationTotalCount,
            Map<Emotion, String> reactionDetails) {

        return new ReactionQueryResponseDto(type, recationTotalCount, reactionDetails);
    }
}
