package today.sesac.versebyverse.badge.message;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.badge.entity.BadgeOutbox;
import today.sesac.versebyverse.badge.entity.MemberCreatedEventPayload;
import today.sesac.versebyverse.badge.entity.OutboxMessageType;
import today.sesac.versebyverse.badge.entity.OutboxStatus;
import today.sesac.versebyverse.badge.entity.PostCreatedEventPayload;
import today.sesac.versebyverse.badge.repository.BadgeOutboxRepository;
import today.sesac.versebyverse.badge.service.BadgeService;
import today.sesac.versebyverse.global.config.RedisStreamConfig;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.repository.MemberRepository;
import today.sesac.versebyverse.post.repository.PostRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class BadgeEventConsumer implements
        StreamListener<String, MapRecord<String, String, String>> {

    private final BadgeService badgeService;

    private final ObjectMapper objectMapper;

    private final MemberRepository memberRepository;

    private final BadgeOutboxRepository badgeOutboxRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onMessage(MapRecord<String, String, String> record) {

        Map<String, String> body = record.getValue();

        String eventId = body.get("eventId");
        String eventType = body.get("eventType");
        String payloadJson = body.get("payload");

        log.info("eventId: {}", eventId);
        log.info("eventType: {}", eventType);
        log.info("payloadJson: {}", payloadJson);

        eventId = eventId.replaceAll("\"", "");
        eventType = eventType.replaceAll("\"", "");
        payloadJson = payloadJson.substring(1, payloadJson.length() - 1)  // 바깥쪽 따옴표 제거
                .replace("\\\"", "\"");                   // 이스케이프된 따옴표 복원

        log.info("eventId: {}", eventId);
        log.info("eventType: {}", eventType);
        log.info("payloadJson: {}", payloadJson);

        log.info("POST_CREATED: {}", OutboxMessageType.POST_CREATED.name());

        try {
            if (OutboxMessageType.POST_CREATED.name().equals(eventType)) {
                PostCreatedEventPayload payload =
                        objectMapper.readValue(payloadJson, PostCreatedEventPayload.class);

                Optional<Member> memberOptional = memberRepository.findById(payload.getAuthorId());
                log.info("memberOptional: {}", memberOptional);
                memberOptional.ifPresent(badgeService::grantPostBadges);
            } else if (OutboxMessageType.MEMBER_CREATED.name().equals(eventType)) {
                MemberCreatedEventPayload payload =
                        objectMapper.readValue(payloadJson, MemberCreatedEventPayload.class);

                Optional<Member> memberOptional = memberRepository.findById(payload.getMemberId());
                log.info("memberOptional: {}", memberOptional);
                memberOptional.ifPresent(badgeService::grantMemberBadges);
            } else {
                log.info("해당하는 경우 없음. eventType: {}", eventType);
                return;
            }

            redisTemplate.opsForStream().acknowledge(
                    record.getStream(),   // 스트림 키 ("badge-events")
                    RedisStreamConfig.CONSUMER_GROUP_NAME,        // 그룹 이름
                    record.getId()        // 처리 완료한 메시지의 ID
            );

        } catch (Exception e) {
            log.info("listen stream error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
