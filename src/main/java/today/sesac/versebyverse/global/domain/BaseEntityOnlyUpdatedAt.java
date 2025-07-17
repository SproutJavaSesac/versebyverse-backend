package today.sesac.versebyverse.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * {@link EntityListeners}를 사용해 수정일시를 자동으로 관리합니다.
 * <p>
 * Entity에서 상속받기만 하면 자동으로 사용 가능합니다. {@link MappedSuperclass}를 이용해 별개의 entity(테이블)는 생성하지 않습니다.
 * </p>
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityOnlyUpdatedAt {

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}