package today.sesac.versebyverse.comment.dto.response;

import java.util.List;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.response.PaginationDto;

/**
 * 댓글 목록 응답 DTO.
 */
public record CommentListResponseDto(

        Long postId,

        List<CommentResponseDto> comments,

        PaginationDto pagination
) {

    /**
     * 페이지 객체를 사용하여 댓글 목록 응답 DTO를 생성합니다.
     *
     * @param postId       게시글 ID
     * @param pageComments 댓글 페이지 정보
     */
    public CommentListResponseDto(
            Long postId,
            Page<Comment> pageComments
    ) {

        this(
                postId,
                IntStream.range(0, pageComments.getContent().size())
                        .mapToObj(i ->
                                CommentResponseDto.of(pageComments.getContent().get(i), i))
                        .toList(),
                new PaginationDto(
                        pageComments.getNumber(),
                        pageComments.getTotalPages(),
                        pageComments.getTotalElements(),
                        pageComments.getSize(),
                        pageComments.hasNext(),
                        pageComments.hasPrevious()
                )
        );
    }
}