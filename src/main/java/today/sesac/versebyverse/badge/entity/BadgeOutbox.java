package today.sesac.versebyverse.badge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;

/**
 * 뱃지 생성 조건을 체크하는 아웃박스 엔티티입니다.
 */
@Getter
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

    private BadgeOutbox(OutboxMessageType messageType, String payload, OutboxStatus status) {

        this.messageType = messageType;
        this.payload = payload;
        this.status = status;
        this.retryCount = 0;
    }

    public static BadgeOutbox create(OutboxMessageType messageType, String payload,
            OutboxStatus status) {

        return new BadgeOutbox(messageType, payload, status);
    }

    public void changeStatus(OutboxStatus status) {

        this.status = status;
    }

    public void increaseRetryCount() {

        this.retryCount++;
    }

}
