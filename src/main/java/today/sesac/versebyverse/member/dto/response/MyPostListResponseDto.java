package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import today.sesac.versebyverse.global.response.PaginationDto;

@Getter
@Setter
@NoArgsConstructor
public class MyPostListResponseDto {

    private List<MyPostSummary> posts;

    private PaginationDto pagination;
}