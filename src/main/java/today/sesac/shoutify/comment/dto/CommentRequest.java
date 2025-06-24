package today.sesac.shoutify.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

	@NotBlank
	@Size(max = 500)
	private String content;
	private Long parentId;
}
