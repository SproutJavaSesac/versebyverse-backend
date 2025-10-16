package today.sesac.versebyverse.global.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.ranking.entity.Ranking;

/**
 * 랭킹이 생성되었을 때 발생하는 이벤트.
 */
@Getter
@RequiredArgsConstructor
public class RankingCreatedEvent {

    private final Member member;

    private final Ranking ranking;
}
