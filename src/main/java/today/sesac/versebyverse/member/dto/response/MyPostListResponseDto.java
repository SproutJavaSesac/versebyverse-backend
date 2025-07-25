package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 사용자가 작성한 게시글 목록 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MyPostListResponseDto {

    private final List<MyPostSummaryDto> posts;

    private final PaginationDto pagination;

    /**
     * 페이지 객체를 사용하여 사용자가 작성한 게시글 목록 응답 DTO를 생성합니다.
     *
     * @param posts 게시글 페이지 정보
     * @return 사용자가 작성한 게시글 목록 응답 DTO
     */
    public static MyPostListResponseDto of(Page<Post> posts) {
        List<MyPostSummaryDto> postSummaries = posts.getContent().stream()
                .map(MyPostSummaryDto::of)
                .toList();

        PaginationDto paginationDto = new PaginationDto(
                posts.getNumber(),
                posts.getTotalPages(),
                posts.getTotalElements(),
                posts.getSize(),
                posts.hasNext(),
                posts.hasPrevious()
        );

        return new MyPostListResponseDto(postSummaries, paginationDto);
    }
}