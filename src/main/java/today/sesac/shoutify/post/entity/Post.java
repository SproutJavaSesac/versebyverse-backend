package today.sesac.shoutify.post.entity;

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

/**
 * 게시판의 게시글을 관리하는 도메인입니다.
 */
@Entity //엔티티임을 표시
@Table(name = "posts") //엔티티<->매핑할 테이블 , 이름 생략시 엔티티 이름 사용, 유니크 제약조건
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //매개변수 없는 생성자를 protected로 만들어줌

public class Post extends BaseEntity {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column //객체 필드를 테이블 컬럼에 매핑
    private Long id;
    /**
     * 비속어 ai 수정 전 게시물 내용입니다.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String afterContents;
    /**
     * 게시물의 제목입니다.
     */
    @Column(length = 255)
    private String title;
    /**
     * 게시물 작성후 ai로 판단되는 게시물에 대한 점수입니다.
     */
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int score = 0;
    /**
     * 게시물 내의 이미지 입니다. url로 저장됩니다.
     */
    @Column(length = 500)
    private String imageUrl;
    /**
     * 게시글 신고 유무입니다. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isReported = false;
    /**
     * 게시글 삭제 유무입니다. soft deleted이므로 삭제되어도 db에는 존재하게됩니다.
     * 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isDeleted = false;
    /**
     * 숨김 유무입니다. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isHidden = false;
    /**
     * 자신의 게시물에 등록하는 이모지 입니다.
     * null일 경우(작성자가 선택하지 않았을 경우) ai가 글의 이모지를 선택해줍니다.
     */
    @Column(length = 20)
    private String my_emoji;

    private Post(String afterContents, String title, Integer score, String imageUrl, String my_emoji) {
        this.afterContents = afterContents;
        this.title = title;
        this.score = score;
        this.imageUrl = imageUrl;
        this.isReported = false; //Boolean은 디폴트가 false
        this.isDeleted = false;
        this.isHidden = false;
        this.my_emoji = my_emoji;
    }

    // 팩토리 메소드
    public static Post create(String afterContents, String title, Integer score, String imageUrl, String my_emoji) {
        return new Post(afterContents, title, score, imageUrl, my_emoji);
    }

}
