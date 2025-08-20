package today.sesac.versebyverse.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * S3 설정을 관리합니다. Spring Cloud AWS 3.1.1에서는 S3Client가 자동으로 Bean으로 등록됩니다.
 */
@Configuration
public class S3Config {

    @Value("${S3_ACCESS_KEY}")
    private String accessKey;

    @Value("${S3_SECRET_ACCESS_KEY")
    private String secretKey;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    @Value("${S3_REGION}")
    private String region;

    @Bean
    public S3Client s3Client() {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
    }
}
