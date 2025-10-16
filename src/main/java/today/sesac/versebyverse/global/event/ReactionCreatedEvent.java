package today.sesac.versebyverse.global.event;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.reaction.utils.TargetType;

/**
 * 반응이 생성되었을 때 발생하는 이벤트.
 */
@Getter
@RequiredArgsConstructor
public class ReactionCreatedEvent {

    private final Long memberId;

    private final Long targetId;

    private final TargetType targetType;

//    /**
//     * 게시글에 반응이 생성되었을 때 발생하는 이벤트의 생성자.
//     *
//     * @param member 반응을 작성한 회원
//     * @param post   반응이 달린 게시글
//     */
//    public ReactionCreatedEvent(Member member, Post post) {
//
//        this.member = member;
//        this.post = post;
//    }
//
//    /**
//     * 댓글에 반응이 생성되었을 때 발생하는 이벤트의 생성자.
//     *
//     * @param member  반응을 작성한 회원
//     * @param comment 반응이 달린 댓글
//     */
//    public ReactionCreatedEvent(Member member, Comment comment) {
//
//        this.member = member;
//        this.comment = comment;
//    }
//
//    public Optional<Post> getPostOptional() {
//
//        return Optional.ofNullable(post);
//    }
//
//    public Optional<Comment> getCommentOptional() {
//
//        return Optional.ofNullable(comment);
//    }
}
