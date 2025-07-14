package today.sesac.shoutify.global.response;

/**
 * 페이지네이션 정보 DTO.
 */
public record PaginationDto(
        int currentPage,
        int totalPages,
        long totalCount,
        int pageSize,
        boolean hasNext,
        boolean hasPrevious
) {

}
