package today.sesac.versebyverse.post.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.global.domain.Concept;

/**
 * 게시글 교정 생성 응답 DTO.
 */
public record PostProofreadCreateResponseDto(
        String taskUuid,  // 이 첨삭 세션 전체를 식별하는 ID
        Long attemptId, // 방금 생성된 이 결과물을 식별하는 ID

        String nickname,

        String afterTitle,

        String afterContent,

        Concept conceptType,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}