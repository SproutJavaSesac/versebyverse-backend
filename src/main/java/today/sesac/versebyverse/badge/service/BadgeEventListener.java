package today.sesac.versebyverse.badge.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import today.sesac.versebyverse.badge.entity.Badge;
import today.sesac.versebyverse.badge.entity.MemberBadge;
import today.sesac.versebyverse.badge.repository.BadgeRepository;
import today.sesac.versebyverse.badge.repository.MemberBadgeRepository;
import today.sesac.versebyverse.global.event.PostCreatedEvent;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * 배지관련 이벤트를 수신하는 클래스.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BadgeEventListener {

    private final PostRepository postRepository;

    private final MemberBadgeRepository memberBadgeRepository;

    private final BadgeRepository badgeRepository;

    /**
     * 게시글이 생성되었을 때 실행되는 메서드.
     * 게시글 생성이 성공적으로 커밋된 후에만 이 메서드를 실행합니다.
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePostCreated(PostCreatedEvent postCreatedEvent) {

        Member member = postCreatedEvent.getMember();

        List<MemberBadge> memberBadgeList = memberBadgeRepository.findByMemberId(member.getId());
        checkAndGrantFirstPostBadge(memberBadgeList, member);
        checkAndGrantTenthPostBadge(memberBadgeList, member);
    }

    private void checkAndGrantFirstPostBadge(List<MemberBadge> memberBadgeList, Member author) {

        Long authorId = author.getId();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals("첫 게시글"));

        long postCount = postRepository.countByAuthorIdAndIsDeletedFalse(authorId);

        if (postCount > 0 && !hasTargetBadge) {
            Badge badge = badgeRepository.findByName("첫 게시글")
                    .orElseThrow(() -> new RuntimeException("첫 게시글 배지를 찾을 수 없습니다"));

            MemberBadge memberBadge = MemberBadge.create(author, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

    private void checkAndGrantTenthPostBadge(List<MemberBadge> memberBadgeList, Member author) {

        Long authorId = author.getId();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals("활발한 작가"));

        long postCount = postRepository.countByAuthorIdAndIsDeletedFalse(authorId);

        if (postCount >= 10 && !hasTargetBadge) {
            log.info("활발한 작가");
            Badge badge = badgeRepository.findByName("활발한 작가")
                    .orElseThrow(() -> new RuntimeException("error"));

            MemberBadge memberBadge = MemberBadge.create(author, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }
}
