package today.sesac.versebyverse.global.config;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 테스트용 S3 설정입니다. 실제 S3 대신 Mock S3Client를 사용합니다.
 */
@TestConfiguration
public class TestS3Config {

    @Bean
    @Primary
    public Object s3Client() {

        return mock(Object.class);
    }
} 