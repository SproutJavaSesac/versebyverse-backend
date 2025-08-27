package today.sesac.versebyverse.comment.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import today.sesac.versebyverse.global.domain.Emotion;

/**
 * 댓글 단건 조회 응답 DTO. 이 DTO는 특정 댓글의 상세 정보를 포함합니다.
 */
public record CommentSingleQueryForAdminResponseDto(

        Long commentId,

        Long postId,

        Long commenterId,

        String commenterNickname,

        Long parentId,

        String content,

        int level,

        int reactionTotalCount,

        Map<Emotion, Integer> reactionDetails,

        Boolean isDeleted,

        Boolean isBlocked,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {

}