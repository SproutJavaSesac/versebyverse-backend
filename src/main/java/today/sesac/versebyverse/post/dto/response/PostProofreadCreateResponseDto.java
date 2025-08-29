package today.sesac.versebyverse.post.dto.response;

import java.time.LocalDateTime;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.global.domain.Genre;

/**
 * 게시글 첨삭 생성 응답 DTO.
 */
public record PostProofreadCreateResponseDto(
        String taskUuid,
        Long attemptId,
        Long authorId,
        String authorNickname,
        String beforeTitle,
        String beforeContent,
        String afterTitle,
        String afterContent,
        Genre genreType,
        Emotion emotionType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}