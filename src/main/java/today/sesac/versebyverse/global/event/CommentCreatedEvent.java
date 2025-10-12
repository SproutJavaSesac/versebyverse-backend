package today.sesac.versebyverse.global.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 댓글이 생성되었을 때 발생하는 이벤트.
 */
@Getter
@RequiredArgsConstructor
public class CommentCreatedEvent {

    private final Member member;    // 댓글 작성자

    private final Post post;

}
