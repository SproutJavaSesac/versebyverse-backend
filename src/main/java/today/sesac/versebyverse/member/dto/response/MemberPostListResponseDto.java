package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.global.response.PaginationDto;

/**
 * 회원이 작성한 게시글 목록 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MemberPostListResponseDto {

    private final List<MemberPostSummaryDto> posts;

    private final PaginationDto pagination;

    /**
     * 페이지 객체를 사용하여 회원이 작성한 게시글 목록 응답 DTO를 생성합니다.
     *
     * @param posts 회원이 작성한 게시글 DTO의 목록
     * @param pagination 페이지네이션 정보
     * @return 회원이 작성한 게시글 목록 응답 DTO
     */
    public static MemberPostListResponseDto of(List<MemberPostSummaryDto> posts, PaginationDto pagination) {

        return new MemberPostListResponseDto(posts, pagination);
    }

}
