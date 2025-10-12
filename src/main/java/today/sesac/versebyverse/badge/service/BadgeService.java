package today.sesac.versebyverse.badge.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.badge.entity.Badge;
import today.sesac.versebyverse.badge.entity.BadgeType;
import today.sesac.versebyverse.badge.entity.MemberBadge;
import today.sesac.versebyverse.badge.exception.BadgeErrorCode;
import today.sesac.versebyverse.badge.exception.BadgeException;
import today.sesac.versebyverse.badge.repository.BadgeRepository;
import today.sesac.versebyverse.badge.repository.MemberBadgeRepository;
import today.sesac.versebyverse.comment.repository.CommentRepository;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * BadgeService는 배지 관련 비즈니스 로직을 처리하는 서비스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeService {

    private final BadgeRepository badgeRepository;

    private final MemberBadgeRepository memberBadgeRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    /**
     * 게시글이 작성되었을 때, 조건을 만족하는 배지가 있으면 획득하는 메서드.
     *
     * @param author 게시글 작성자 객체.
     */
    public void grantPostBadges(Member author) {

        List<MemberBadge> memberBadgeList = memberBadgeRepository.findByMemberId(author.getId());

        checkAndGrantFirstPostBadge(memberBadgeList, author);
        checkAndGrantTenthPostBadge(memberBadgeList, author);
    }

    private void checkAndGrantFirstPostBadge(List<MemberBadge> memberBadgeList, Member author) {

        String targetBadgeName = BadgeType.FIRST_POST.getBadgeName();

        Long authorId = author.getId();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals(targetBadgeName));

        int postCount = postRepository.countByAuthorIdAndIsDeletedFalse(authorId);

        if (postCount > 0 && !hasTargetBadge) {
            Badge badge = badgeRepository.findByName(targetBadgeName).orElseThrow(
                    () -> new BadgeException(BadgeErrorCode.BADGE_NOT_FOUND,
                            BadgeType.FIRST_POST.getBadgeName()));

            MemberBadge memberBadge = MemberBadge.create(author, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

    private void checkAndGrantTenthPostBadge(List<MemberBadge> memberBadgeList, Member author) {

        String targetBadgeName = BadgeType.NOVICE_WRITER.getBadgeName();

        Long authorId = author.getId();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals(targetBadgeName));

        int postCount = postRepository.countByAuthorIdAndIsDeletedFalse(authorId);

        if (postCount >= 10 && !hasTargetBadge) {
            Badge badge = badgeRepository.findByName(targetBadgeName).orElseThrow(
                    () -> new BadgeException(BadgeErrorCode.BADGE_NOT_FOUND,
                            BadgeType.NOVICE_WRITER.getBadgeName()));

            MemberBadge memberBadge = MemberBadge.create(author, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

    /**
     * 댓글이 생성되었을 때, 조건을 만족하는 배지가 있으면 획득하는 메서드.
     *
     * @param member 댓글 작성자
     * @param post   댓글일 작성된 게시글
     */
    public void grantCommentBadges(Member member, Post post) {

        List<MemberBadge> commenterMemberBadgeList =
                memberBadgeRepository.findByMemberId(member.getId());

        checkAndGrantFirstCommentBadge(commenterMemberBadgeList, member);
        checkAndGrantFifthCommentBadge(commenterMemberBadgeList, member);

        List<MemberBadge> authorMemberBadgeList =
                memberBadgeRepository.findByMemberId(post.getAuthor().getId());

        checkAndGrantEarnFifthCommentBadges(authorMemberBadgeList, post);
    }

    private void checkAndGrantFirstCommentBadge(List<MemberBadge> memberBadgeList, Member member) {

        String targetBadgeName = BadgeType.FIRST_COMMENT.getBadgeName();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals(targetBadgeName));

        int commentCount = commentRepository.countByCommenterIdAndIsDeletedFalse(member.getId());

        if (commentCount > 0 && !hasTargetBadge) {
            Badge badge = badgeRepository.findByName(targetBadgeName).orElseThrow(
                    () -> new BadgeException(BadgeErrorCode.BADGE_NOT_FOUND,
                            targetBadgeName));

            MemberBadge memberBadge = MemberBadge.create(member, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

    private void checkAndGrantFifthCommentBadge(List<MemberBadge> memberBadgeList, Member member) {

        String targetBadgeName = BadgeType.REACTION_FAIRY.getBadgeName();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals(targetBadgeName));

        int commentCount = commentRepository.countByCommenterIdAndIsDeletedFalse(member.getId());

        if (commentCount >= 5 && !hasTargetBadge) {
            Badge badge = badgeRepository.findByName(targetBadgeName).orElseThrow(
                    () -> new BadgeException(BadgeErrorCode.BADGE_NOT_FOUND,
                            targetBadgeName));

            MemberBadge memberBadge = MemberBadge.create(member, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

    private void checkAndGrantEarnFifthCommentBadges(List<MemberBadge> memberBadgeList, Post post) {

        String targetBadgeName = BadgeType.POPULAR_POST.getBadgeName();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals(targetBadgeName));

        int commentCount =
                commentRepository.countByPostIdAndIsDeletedFalseAndIsBlockedFalse(post.getId());

        if (commentCount >= 5 && !hasTargetBadge) {
            Badge badge = badgeRepository.findByName(targetBadgeName).orElseThrow(
                    () -> new BadgeException(BadgeErrorCode.BADGE_NOT_FOUND,
                            targetBadgeName));

            MemberBadge memberBadge = MemberBadge.create(post.getAuthor(), badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

    /**
     * 회원이 생성되었을 때, 조건을 만족하는 배지가 있으면 획득하는 메서드.
     *
     * @param member 가입한 회원 객체.
     */
    public void grantMemberBadges(Member member) {

        List<MemberBadge> memberBadgeList = memberBadgeRepository.findByMemberId(member.getId());

        checkAndGrantMemberCreatedBadge(memberBadgeList, member);
    }

    private void checkAndGrantMemberCreatedBadge(List<MemberBadge> memberBadgeList, Member member) {

        String targetBadgeName = BadgeType.FIRST_SIGNUP.getBadgeName();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals(targetBadgeName));

        if (!hasTargetBadge) {
            Badge badge = badgeRepository.findByName(targetBadgeName).orElseThrow(
                    () -> new BadgeException(BadgeErrorCode.BADGE_NOT_FOUND,
                            BadgeType.FIRST_SIGNUP.getBadgeName()));

            MemberBadge memberBadge = MemberBadge.create(member, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

}
