package today.sesac.versebyverse.post.dto.request;

public record PostProofreadPublishRequestDto(

        // 선택된(발행할) 첨삭 ID
        Long chosenAttemptId,

        /*
         * 이미지 URL.
         */
        String imageUrl) {

}