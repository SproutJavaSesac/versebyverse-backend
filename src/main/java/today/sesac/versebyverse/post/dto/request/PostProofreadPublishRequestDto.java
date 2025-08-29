package today.sesac.versebyverse.post.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * 게시글 첨삭 발행 요청 DTO.
 */
public record PostProofreadPublishRequestDto(

        // 선택된(발행할) 첨삭 ID
        @NotNull
        Long chosenAttemptId,

        String imageUrl) {

}