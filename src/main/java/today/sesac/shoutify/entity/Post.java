package today.sesac.shoutify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;

@Entity //엔티티임을 표시
@Table(name = "posts") //엔티티<->매핑할 테이블 , 이름 생략시 엔티티 이름 사용, 유니크 제약조건
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //매개변수 없는 생성자를 protected로 만들어줌

public class Post extends BaseEntity {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column //객체 필드를 테이블 컬럼에 매핑
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String afterContents;

    @Column(length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer score = 0;

    @Column(name = "image", length = 500)
    private String image; //url?

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isReported = false;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isDeleted = false;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isHidden = false;

    @Column(name = "my_emoji", length = 20)
    private String my_emoji;

    private Post(String afterContents, String title, Integer score, String image, String my_emoji) {
        this.afterContents = afterContents;
        this.title = title;
        this.score = score;
        this.image = image;
        this.isReported = false; //Boolean은 디폴트가 false
        this.isDeleted = false;
        this.isHidden = false;
        this.my_emoji = my_emoji;
    }

    // 팩토리 메소드
    public static Post create(String afterContents, String title, Integer score, String image,
        String my_emoji) {
        return new Post(afterContents, title, score, image, my_emoji);
    }

}
