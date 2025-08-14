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

    private final BadgeService badgeService;

    /**
     * 게시글이 생성되었을 때 실행되는 메서드.
     * 게시글 생성이 성공적으로 커밋된 후에만 이 메서드를 실행합니다.
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePostCreated(PostCreatedEvent postCreatedEvent) {

        Member member = postCreatedEvent.getMember();

        badgeService.checkAndGrantPostBadges(member);

    }

}
