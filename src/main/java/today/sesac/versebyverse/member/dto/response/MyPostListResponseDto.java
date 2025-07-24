package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.post.entity.Post;

@Getter
@AllArgsConstructor
public class MyPostListResponseDto {

    private final List<MyPostSummaryDto> posts;

    private final PaginationDto pagination;

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