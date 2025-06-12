package today.sesac.shoutify.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA 설정을 관리합니다.
 * <p>
 *     생성일, 수정일 자동 관리 기능을 이용하기 위해 JPA Auditing을 설정합니다.
 *    설정을 각각 관리하기 위해 ShoutifyApplication가 아니라 config로 관리합니다.
 * </p>
 * @see today.sesac.shoutify.global.domain.BaseEntity  BaseEntity
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
