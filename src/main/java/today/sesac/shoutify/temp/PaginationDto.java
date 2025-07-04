package today.sesac.shoutify.temp;

import lombok.Builder;
import lombok.Data;

/**
 * 페이지네이션 정보 DTO.
 */
@Data
@Builder
public class PaginationDto {

    /**
     * 현재 페이지 번호.
     */
    private int currentPage;

    /**
     * 전체 페이지 수.
     */
    private int totalPage;

    /**
     * 전체 항목 수.
     */
    private int totalCount;

    /**
     * 한 페이지당 항목 수.
     */
    private int pageSize;

    /**
     * 다음 페이지 존재 여부.
     */
    private boolean hasNext;

    /**
     * 이전 페이지 존재 여부.
     */
    private boolean hasPrev;
}