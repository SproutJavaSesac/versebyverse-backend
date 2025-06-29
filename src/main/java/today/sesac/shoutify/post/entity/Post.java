package today.sesac.shoutify.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.global.domain.Emotion;
import today.sesac.shoutify.member.entity.Member;

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
    private Long id;
    /**
     * 게시물을 작성한 회원입니다
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member author;
    /**
     * ai 수정 전 게시물 내용입니다.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String beforeContent;
    /**
     * ai 수정 후 게시물 내용입니다.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String afterContent;
    /**
     * 게시물의 제목입니다.
     */
    @Column(nullable = false)
    private String title;
    /**
     * 게시물 내의 이미지 입니다. url로 저장됩니다.
     */
    @Column(length = 500)
    private String imageUrl;
    /**
     * 게시글 신고 유무입니다. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isReported;
    /**
     * 게시글 삭제 유무입니다. soft deleted이므로 삭제되어도 db에는 존재하게됩니다. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isDeleted;
    /**
     * 숨김 유무입니다. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isHidden;
    /**
     * 자신의 게시물에 등록하는 반응 입니다. null일 경우(작성자가 선택하지 않았을 경우) ai가 글의 이모지를 선택해줍니다.
     */
    @Enumerated(EnumType.STRING)
    private Emotion emotionType;
    /**
     * 글의 컨셉입니다. enum타입으로 총 5가지 입니다.
     */
    @Enumerated(EnumType.STRING)
    private Concept conceptType;

    private Post(Member author, String beforeContent, String afterContent, String title,
            String imageUrl, Emotion emotionType, Concept conceptType) {
        this.author = author;
        this.beforeContent = beforeContent;
        this.afterContent = afterContent;
        this.title = title;
        this.imageUrl = imageUrl;
        this.isReported = false;
        this.isDeleted = false;
        this.isHidden = false;
        this.emotionType = emotionType;
        this.conceptType = conceptType;
    }

    public static Post createPost(Member author, String beforeContent, String afterContent,
            String title, String imageUrl, Emotion emotionType,
            Concept conceptType) {
        return new Post(author, beforeContent, afterContent, title, imageUrl, emotionType,
                conceptType);
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void hide() {
        this.isHidden = true;
    }

    public void unhide() {
        this.isHidden = false;
    }
}
