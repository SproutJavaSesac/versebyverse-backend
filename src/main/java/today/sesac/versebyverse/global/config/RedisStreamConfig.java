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

        createStreamConsumerGroup();

        // 1. 컨테이너 옵션 설정
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

        // 3. 리스너 등록
        Subscription subscription = container.receive(
                Consumer.from(CONSUMER_GROUP_NAME, "badge-consumer-1"),
                StreamOffset.create(STREAM_KEY, ReadOffset.lastConsumed()),
                badgeEventConsumer
        );

        container.start();
        return subscription;
    }

    private void createStreamConsumerGroup() {

        if (!redisTemplate.hasKey(STREAM_KEY)) {
            RedisClusterAsyncCommands commands = (RedisClusterAsyncCommands) redisTemplate
                    .getConnectionFactory()
                    .getConnection()
                    .getNativeConnection();

            CommandArgs<String, String> args = new CommandArgs<>(StringCodec.UTF8)
                    .add(CommandKeyword.CREATE)
                    .add(STREAM_KEY)
                    .add(CONSUMER_GROUP_NAME)
                    .add("0")
                    .add("MKSTREAM");

            commands.dispatch(CommandType.XGROUP, new StatusOutput(StringCodec.UTF8), args);
        } else {
            if (!isStreamConsumerGroupExist()) {
                this.redisTemplate.opsForStream()
                        .createGroup(STREAM_KEY, ReadOffset.from("0"), CONSUMER_GROUP_NAME);
            }
        }
    }

    private boolean isStreamConsumerGroupExist() {

        try {
            return redisTemplate.opsForStream()
                    .groups(STREAM_KEY)
                    .stream()
                    .anyMatch(group -> CONSUMER_GROUP_NAME.equals(group.groupName()));
        } catch (Exception e) {
            return false;
        }
    }

}
