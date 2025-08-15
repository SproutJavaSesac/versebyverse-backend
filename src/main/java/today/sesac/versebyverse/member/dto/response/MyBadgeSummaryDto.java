package today.sesac.versebyverse.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자가 보유한 배지 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MyBadgeSummaryDto {

    private Long badgeId;

    private String name;

    private String description;

    /**
     * 사용자가 보유한 배지 DTO를 반환합니다.
     *
     * @param name 배지의 이름
     * @param description 배지 상세 설명
     * @return 사용자가 보유한 배지 DTO
     */
    public static MyBadgeSummaryDto of(Long badgeId, String name, String description) {
        return new MyBadgeSummaryDto(badgeId, name, description);
    }
}
