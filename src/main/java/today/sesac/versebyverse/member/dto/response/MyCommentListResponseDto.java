package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.response.PaginationDto;

/**
 * 사용자가 작성한 댓글 목록 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MyCommentListResponseDto {

    private List<MyCommentSummaryDto> comments;

    private PaginationDto pagination;

    /**
     * 페이지 객체를 사용하여 사용자가 작성한 댓글 목록 응답 DTO를 생성합니다.
     *
     * @param comments 댓글 페이지 정보
     * @return 사용자가 작성한 댓글 목록 응답 DTO
     */
    public static MyCommentListResponseDto of(Page<Comment> comments) {
        List<MyCommentSummaryDto> commentSummaries = comments.getContent().stream()
                .map(MyCommentSummaryDto::of)
                .toList();

        PaginationDto paginationDto = new PaginationDto(
                comments.getNumber(),
                comments.getTotalPages(),
                comments.getTotalElements(),
                comments.getSize(),
                comments.hasNext(),
                comments.hasPrevious()
        );

        return new MyCommentListResponseDto(commentSummaries, paginationDto);
    }
}