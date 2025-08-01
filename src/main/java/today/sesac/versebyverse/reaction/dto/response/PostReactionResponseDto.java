package today.sesac.versebyverse.reaction.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 게시글 반응하기 응답 dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class PostReactionResponseDto {
    //추가 or 변경 or 삭제된 감정
    public final Emotion type;

    //반응 총 갯수
    public final int recationTotalCount;

    //반응별 갯수
    public final Map<Emotion, String> reactionDetails;

}
