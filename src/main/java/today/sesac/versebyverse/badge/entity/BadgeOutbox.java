package today.sesac.versebyverse.badge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;

/**
 * 뱃지 생성 조건을 체크하는 아웃박스 엔티티입니다.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BadgeOutbox extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long messageId;

    @Enumerated(EnumType.STRING)
    private OutboxMessageType messageType;

    private String payload;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    private int retryCount;

}
