package today.sesac.versebyverse.reaction.dto.response;

import java.util.Map;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 반응하기 dto.
 *
 * @param type               현재 사용자의 반응
 * @param reactionTotalCount 게시글 or 댓글의 반응 총 갯수
 * @param reactionDetails    게시글 or 댓글의 반응별 갯수
 */
public record ReactionResponseDto(
        //추가 or 변경 or 삭제된 감정
        Emotion type,

        //반응 총 갯수
        int reactionTotalCount,

        //반응별 갯수
        Map<Emotion, Integer> reactionDetails
) {

    /**
     * of 정적 팩토리 메서드.
     */
    public static ReactionResponseDto of(Emotion type, int recationTotalCount,
            Map<Emotion, Integer> reactionDetails) {

        return new ReactionResponseDto(type, recationTotalCount, reactionDetails);
    }
}
