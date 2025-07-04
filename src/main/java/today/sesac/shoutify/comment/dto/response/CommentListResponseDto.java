package today.sesac.shoutify.comment.dto.response;

import java.util.List;
import org.springframework.data.domain.Page;
import today.sesac.shoutify.comment.entity.Comment;

/**
 * 댓글 목록 응답 DTO.
 */
public record CommentListResponseDto(

        int totalCount,

        Long postId,

        List<CommentResponseDto> comments,

        PaginationDto pagination
) {

    /**
     * 페이지 객체를 사용하여 댓글 목록 응답 DTO를 생성합니다.
     *
     * @param totalCount   총 댓글 개수
     * @param postId       게시글 ID
     * @param pageComments 댓글 페이지 정보
     */
    public CommentListResponseDto(
            int totalCount,
            Long postId,
            Page<Comment> pageComments
    ) {

        this(
                totalCount,
                postId,
                pageComments.getContent().stream()
                        .map(CommentResponseDto::of)
                        .toList(),
                new PaginationDto(
                        pageComments.getNumber() + 1, // 페이지는 0부터 시작하므로 +1
                        pageComments.getTotalPages(),
                        pageComments.getSize(),
                        pageComments.hasNext(),
                        pageComments.hasPrevious()
                )
        );
    }
}
