package today.sesac.versebyverse.badge.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.badge.entity.BadgeOutbox;
import today.sesac.versebyverse.badge.entity.OutboxStatus;
import today.sesac.versebyverse.badge.repository.BadgeOutboxRepository;

/**
 * 배지 아웃박스를 처리하는 클래스입니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BadgeOutboxProcessor {

    private final BadgeOutboxRepository badgeOutboxRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String STREAM_KEY = "badge-events";

    /**
     * 데이터베이스에 저장된 아웃박스 테이블을 확인하고, 동작을 수행하는 메서드입니다. 1초마다 한 번씩 아웃박스 테이블을 확인합니다.
     */
    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void processMessages() {

        List<BadgeOutbox> badgeOutboxList = badgeOutboxRepository.findAll();

        for (BadgeOutbox badgeOutbox : badgeOutboxList) {
            if (badgeOutbox.getStatus().name().equals(OutboxStatus.WAITING.name())) {
                try {
                    Map<String, String> messageBody = new HashMap<>();
                    messageBody.put("eventId", badgeOutbox.getId().toString());
                    messageBody.put("eventType", badgeOutbox.getMessageType().name());
                    messageBody.put("payload", badgeOutbox.getPayload());
                    messageBody.put("publishedAt", String.valueOf(System.currentTimeMillis()));

                    log.info("payload: {}", badgeOutbox.getPayload());

                    redisTemplate.opsForStream().add(STREAM_KEY, messageBody);

                    log.info("레디스 스트림으로 저장. messageBody: {}", messageBody);

                    badgeOutbox.changeStatus(OutboxStatus.DONE);
                    badgeOutboxRepository.save(badgeOutbox);

                } catch (Exception e) {
                    log.error("레디스에 연결 안됨.");
                    badgeOutbox.increaseRetryCount();
                    badgeOutboxRepository.save(badgeOutbox);
                }
            }

        }
    }
}
