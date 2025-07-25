package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.response.PaginationDto;

@Getter
@AllArgsConstructor
public class MyCommentListResponseDto {

    private List<MyCommentSummaryDto> comments;

    private PaginationDto pagination;

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