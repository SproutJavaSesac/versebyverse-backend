package today.sesac.versebyverse.global.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import today.sesac.versebyverse.member.entity.Member;

/**
 * 게시글이 생성되었을 때 발생하는 이벤트.
 */
@Getter
@RequiredArgsConstructor
public class PostCreatedEvent {

    private final Member member;

}
