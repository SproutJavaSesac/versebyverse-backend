package today.sesac.versebyverse.post.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.global.domain.Genre;

/**
 * 게시글 첨삭 생성 응답 DTO.
 */
public record PostProofreadCreateResponseDto(
        String taskUuid,
        Long attemptId,
        String nickname,
        String afterTitle,
        String afterContent,
        Genre genreType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}