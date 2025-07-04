package today.sesac.shoutify.member.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyCommentListResponseDto {

    private List<MyCommentSummary> comments;
    private int currentPage;
    private int totalPage;
    private int totalCount;
    private int pageSize;
    private boolean hasNext;
    private boolean hasPrev;
}
