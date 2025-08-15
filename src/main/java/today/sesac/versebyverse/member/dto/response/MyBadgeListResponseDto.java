package today.sesac.versebyverse.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.badge.entity.MemberBadge;

/**
 * 사용자가 보유한 배지 목록 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MyBadgeListResponseDto {

    private List<MyBadgeSummaryDto> badgeSummaries;

    /**
     * 사용자가 보유한 배지 목록을 바탕으로 배지 목록 응답 DTO를 생성합니다.
     *
     * @param badges 사용자가 보유한 배지 목록
     * @return 사용자가 보유한 배지 목록 응답 DTO
     */
    public static MyBadgeListResponseDto of(List<MemberBadge> badges) {

        List<MyBadgeSummaryDto> badgeSummaries = convertBadgesToSummaries(badges);

        return new MyBadgeListResponseDto(badgeSummaries);
    }

    private static List<MyBadgeSummaryDto> convertBadgesToSummaries(List<MemberBadge> badges) {
        return badges.stream()
                .map(badge -> MyBadgeSummaryDto.of(
                        badge.getId(),
                        badge.getBadge().getName(),
                        badge.getBadge().getDescription()))
                .toList();
    }
}
