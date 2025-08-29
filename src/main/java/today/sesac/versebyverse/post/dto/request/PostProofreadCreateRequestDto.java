package today.sesac.versebyverse.post.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.global.domain.Genre;

/**
 * 게시글 첨삭 생성 요청 DTO.
 */
public record PostProofreadCreateRequestDto(

        @NotBlank
        String title,

        @NotNull
        Genre genreType,

        Emotion emotionType,

        @NotBlank
        @Length(max = 1000)
        String content,

        // 재첨삭 요청 시, 현재 Task UUID (없을 시 null)
        @Nullable
        String taskUuid
) {

}