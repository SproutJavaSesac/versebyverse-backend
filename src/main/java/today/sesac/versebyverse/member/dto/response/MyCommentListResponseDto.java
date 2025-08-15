package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
     * @param comments 내가 작성한 댓글 DTO의 목록
     * @param pagination 페이지네이션 정보
     * @return 사용자가 작성한 댓글 목록 응답 DTO
     */
    public static MyCommentListResponseDto of(List<MyCommentSummaryDto> comments, PaginationDto pagination) {

        return new MyCommentListResponseDto(comments, pagination);
    }

}