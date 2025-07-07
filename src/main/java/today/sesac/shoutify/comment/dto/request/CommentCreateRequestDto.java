package today.sesac.shoutify.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 댓글 작성 요청 DTO.
 */
public record CommentCreateRequestDto(
        @Size(max = 500, message = "댓글 내용은 최대 500자까지 입력할 수 있습니다.")
        @NotBlank
        String content,
        Long parentId
) {

}
