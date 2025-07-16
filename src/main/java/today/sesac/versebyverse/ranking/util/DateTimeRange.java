package today.sesac.versebyverse.ranking.util;

import java.time.LocalDateTime;

/**
 * 날짜 범위를 나타내는 레코드 클래스. 시작 시각과 종료 시각을 포함합니다.
 *
 * @param startDateTime 시작 시각
 * @param endDateTime   종료 시각
 */
public record DateTimeRange(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {

}