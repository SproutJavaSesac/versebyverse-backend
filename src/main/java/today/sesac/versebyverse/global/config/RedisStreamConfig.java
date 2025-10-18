package today.sesac.versebyverse.global.config;

import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.output.StatusOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandKeyword;
import io.lettuce.core.protocol.CommandType;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.scheduling.annotation.EnableScheduling;
import today.sesac.versebyverse.badge.message.BadgeEventConsumer;

@Configuration
@EnableScheduling
public class RedisStreamConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    private final BadgeEventConsumer badgeEventConsumer;

    private final RedisTemplate<String, Object> redisTemplate;

    public static final String STREAM_KEY = "badge-events";

    public static final String CONSUMER_GROUP_NAME = "badge-group";

    public RedisStreamConfig(RedisConnectionFactory redisConnectionFactory,
            BadgeEventConsumer badgeEventConsumer, RedisTemplate<String, Object> redisTemplate) {

        this.redisConnectionFactory = redisConnectionFactory;
        this.badgeEventConsumer = badgeEventConsumer;
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public Subscription subscription() {

        createStreamConsumerGroup(STREAM_KEY, CONSUMER_GROUP_NAME);

        // 1. 컨테이너 옵션 설정 (예: 한 번에 몇 개씩 가져올지 등)
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>>
                options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        .pollTimeout(Duration.ofSeconds(1)) // 1초마다 폴링
                        .batchSize(10) // 한 번에 최대 10개
                        .build();

        // 2. 컨테이너 생성
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> container =
                StreamMessageListenerContainer.create(redisConnectionFactory, options);

        // 3. 리스너 등록 (가장 중요한 부분!)
        Subscription subscription = container.receive(
                // Consumer.from("그룹이름", "소비자이름")
                Consumer.from(CONSUMER_GROUP_NAME, "badge-consumer-1"), // ★ 바로 이것이 '그룹'입니다!
                // StreamOffset.create("스트림키", ReadOffset.lastConsumed())
                StreamOffset.create(STREAM_KEY, ReadOffset.lastConsumed()),
                badgeEventConsumer // 어떤 리스너가 처리할지 지정
        );

        container.start(); // 컨테이너 시작
        return subscription;
    }

    private void createStreamConsumerGroup(String streamKey, String consumerGroupName) {

        if (!redisTemplate.hasKey(streamKey)) {
            RedisClusterAsyncCommands commands = (RedisClusterAsyncCommands) redisTemplate
                    .getConnectionFactory()
                    .getConnection()
                    .getNativeConnection();

            CommandArgs<String, String> args = new CommandArgs<>(StringCodec.UTF8)
                    .add(CommandKeyword.CREATE)
                    .add(streamKey)
                    .add(consumerGroupName)
                    .add("0")
                    .add("MKSTREAM");

            commands.dispatch(CommandType.XGROUP, new StatusOutput(StringCodec.UTF8), args);
        } else {
            if (!isStreamConsumerGroupExist(streamKey, consumerGroupName)) {
                this.redisTemplate.opsForStream()
                        .createGroup(streamKey, ReadOffset.from("0"), consumerGroupName);
            }
        }
    }

    private boolean isStreamConsumerGroupExist(String streamKey, String consumerGroupName) {

        try {
            return redisTemplate.opsForStream()
                    .groups(streamKey)
                    .stream()
                    .anyMatch(group -> consumerGroupName.equals(group.groupName()));
        } catch (Exception e) {
            // Stream이 존재하지 않거나 다른 오류가 발생한 경우
            return false;
        }
    }

}
