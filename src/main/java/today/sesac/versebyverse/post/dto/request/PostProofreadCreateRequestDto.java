package today.sesac.versebyverse.post.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.global.domain.Emotion;

public record PostProofreadCreateRequestDto(

        /*
         * 변환 전 사용자가 작성하는 게시물 제목입니다.
         */
        @NotBlank
        String title,

        /*
         * 게시물의 컨셉타입입니다.
         */
        @NotNull
        Concept conceptType,

        /*
         * 감정 타입 null일 경우 ai가 감정 분석을 해줍니다.
         */
        Emotion emotionType,

        /*
         * 변환 전 사용자가 쓴 게시물 내용입니다.
         */
        @NotBlank
        @Length(max = 1000)
        String content,

        // 같은 게시글에 대한 교정임을 알려주는 UUID입니다.
        @Nullable
        String taskUuid
) {

}