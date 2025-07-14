package today.sesac.shoutify.post.dto.response;

import java.util.List;
import today.sesac.shoutify.global.response.PaginationDto;

/**
 * 페이지 데이터 + 페이지네이션 정보를 함께 담는 응답 dto.
 *
 * @param <T> 항목 리스트 타입
 */
public record PageResponseDto<T>(
        List<T> items,
        PaginationDto pagination) {
}
