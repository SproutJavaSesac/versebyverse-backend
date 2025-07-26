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
     * @param pagePosts 게시글 페이지 정보
     * @return 사용자가 작성한 게시글 목록 응답 DTO
     */
    public static MyPostListResponseDto of(Page<Post> pagePosts) {

        List<MyPostSummaryDto> postSummaries = convertPostsToSummaries(pagePosts);

        PaginationDto paginationDto = getPaginationDto(pagePosts);

        return new MyPostListResponseDto(postSummaries, paginationDto);
    }

    private static List<MyPostSummaryDto> convertPostsToSummaries(Page<Post> pagePosts) {
        return pagePosts.getContent().stream()
                .map(MyPostSummaryDto::of)
                .toList();
    }

    private static PaginationDto getPaginationDto(Page<Post> pagePosts) {
        return new PaginationDto(
                pagePosts.getNumber(),
                pagePosts.getTotalPages(),
                pagePosts.getTotalElements(),
                pagePosts.getSize(),
                pagePosts.hasNext(),
                pagePosts.hasPrevious()
        );
    }

}