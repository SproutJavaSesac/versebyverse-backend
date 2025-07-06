package today.sesac.shoutify.comment.dto.response;

/**
 * 페이지네이션 정보 DTO.
 *
 * <p>현재 페이지, 총 페이지 수, 페이지 크기, 다음 페이지 여부, 이전 페이지 여부를 포함합니다.</p>
 */
public record PaginationDto(
        int currentPage,
        int totalPages,
        int pageSize,
        boolean hasNext,
        boolean hasPrevious
) {

}
