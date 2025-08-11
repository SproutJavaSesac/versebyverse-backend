package today.sesac.versebyverse.badge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;

/**
 * 배지 엔티티.
 */
@Getter
@Entity
@Table(name = "badges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(length = 500)
    private String imageUrl;    //TODO: 이미지 동적으로 추가할 경우를 생각해 imageUrl 필드 미리 추가, 현재는 프론트에서 이미지 구현

    private Badge(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    /**
     * 배지를 생성합니다.
     *
     * @param name 배지의 이름
     * @param description 배지의 상세 설명
     * @param imageUrl 배지의 이미지
     * @return 새로운 배지 엔티티
     */
    public static Badge create(String name, String description, String imageUrl) {
        return new Badge(name, description, imageUrl);
    }
}
