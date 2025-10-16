package today.sesac.versebyverse.badge.message;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.badge.entity.BadgeOutbox;
import today.sesac.versebyverse.badge.repository.BadgeOutboxRepository;

/**
 * 배지 아웃박스를 처리하는 클래스입니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BadgeOutboxProcessor {

    private final BadgeOutboxRepository badgeOutboxRepository;

    /**
     * 데이터베이스에 저장된 아웃박스 테이블을 확인하고, 동작을 수행하는 메서드입니다. 1초마다 한 번씩 아웃박스 테이블을 확인합니다.
     */
    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void processMessages() {

        List<BadgeOutbox> badgeOutboxList = badgeOutboxRepository.findAll();

        for (BadgeOutbox badgeOutbox : badgeOutboxList) {
            log.info("badgeOutbox = {}", badgeOutbox);
        }
    }
}
