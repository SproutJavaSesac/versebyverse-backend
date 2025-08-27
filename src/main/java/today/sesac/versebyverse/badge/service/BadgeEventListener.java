package today.sesac.versebyverse.badge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import today.sesac.versebyverse.global.event.MemberCreatedEvent;
import today.sesac.versebyverse.global.event.PostCreatedEvent;
import today.sesac.versebyverse.member.entity.Member;

/**
 * 배지관련 이벤트를 수신하는 클래스.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BadgeEventListener {

    private final BadgeService badgeService;

    /**
     * 게시글이 생성되었을 때 실행되는 메서드. 게시글 생성이 성공적으로 커밋된 후에만 이 메서드를 실행합니다.
     *
     * @param postCreatedEvent 게시글 생성 이벤트
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePostCreated(PostCreatedEvent postCreatedEvent) {

        Member member = postCreatedEvent.getMember();

        badgeService.grantPostBadges(member);

    }

    /**
     * 회원이 생성되었을 때 실행되는 메서드. 회원 생성이 성공적으로 커밋된 후에만 이 메서드를 실행합니다.
     *
     * @param memberCreatedEvent 회원 생성 이벤트
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleMemberCreated(MemberCreatedEvent memberCreatedEvent) {

        Member member = memberCreatedEvent.getMember();

        badgeService.grantMemberBadges(member);
    }

}
