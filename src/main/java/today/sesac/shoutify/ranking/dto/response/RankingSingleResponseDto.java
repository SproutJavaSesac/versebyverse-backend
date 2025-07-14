package today.sesac.shoutify.ranking.dto.response;

import today.sesac.shoutify.ranking.entity.Ranking;

/**
 * 단일 순위(랭킹) 정보를 담는 DTO.
 *
 * @param memberId              회원 ID
 * @param memberNickname        회원 닉네임
 * @param memberProfileImageUrl 회원 프로필 이미지 URL
 * @param categoryValue         카테고리 값 (예: 특정 카테고리의 점수)
 * @param rank                  현재 순위
 * @param previousRank          이전 순위
 * @param rankChange            순위 변화 (예: 신규, 상승, 하락, 유지 등)
 */
public record RankingSingleResponseDto(
        Long memberId,
        String memberNickname,
        String memberProfileImageUrl,
        int categoryValue,
        int rank,
        Integer previousRank,
        String rankChange
) {

    /**
     * 순위(랭킹) 정보를 담는 생성자.
     *
     * @param ranking    순위(랭킹) 정보
     * @param rankChange 순위 변화 (예: 신규, 상승, 하락, 유지 등)
     */
    public RankingSingleResponseDto(
            Ranking ranking,
            String rankChange
    ) {

        this(
                ranking.getMember().getId(),
                ranking.getMember().getNickname(),
                ranking.getMember().getProfileImageUrl(),
                ranking.getScore(),
                ranking.getRanks(),
                ranking.getPreviousRank(),
                rankChange
        );
    }

}