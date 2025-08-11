package today.sesac.versebyverse.reaction.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.reaction.dto.response.ReactionResponseDto;
import today.sesac.versebyverse.reaction.repository.ReactionRepository;

/**
 * 게시글,댓글별 감정의 총 갯수, 감정별 갯수 표시를 위한 메서드.
 */
public class ReactionUtils {
    public static ReactionResponseDto addCountByReactionType(Emotion type, Long targetId,
                                                             TargetType targetType,
                                                             ReactionRepository reactionRepository
    ) {
        List<Object[]> counts;

        if (targetType.equals(TargetType.POST)) {
            counts = reactionRepository.countReactionsByPostId(targetId);
        } else {
            counts = reactionRepository.countReactionsByCommentId(targetId);
        }

        Map<Emotion, String> reactionDetails = counts.stream().collect(
                Collectors.toMap(
                        row -> (Emotion) row[0],
                        row -> String.valueOf(row[1])
                )
        );

        int reactionCount = reactionDetails.values().stream()
                .mapToInt(Integer::parseInt)
                .sum();

        return ReactionResponseDto.of(type, reactionCount, reactionDetails);
    }
}
