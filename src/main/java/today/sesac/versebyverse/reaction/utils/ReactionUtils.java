package today.sesac.versebyverse.reaction.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.reaction.dto.response.PostReactionResponseDto;
import today.sesac.versebyverse.reaction.repository.ReactionRepository;

public class ReactionUtils {
    public static PostReactionResponseDto addCountbyReactionType(Emotion type, Long targetId,
                                                                 TargetType targetType,
                                                                 ReactionRepository reactionRepository
    ) {
        List<Object[]> counts;

        if (type.equals(TargetType.POST)) {
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

        return PostReactionResponseDto.of(type, reactionCount, reactionDetails);
    }
}
