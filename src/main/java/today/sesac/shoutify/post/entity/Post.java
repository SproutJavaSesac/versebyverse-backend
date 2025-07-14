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
     * 게시물을 작성한 회원.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member author;
    /**
     * ai 수정 전 게시물 내용.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String beforeContent;

    /**
     * ai 수정 후 게시물 내용.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String afterContent;

    /**
     * 게시물 ai 변환 전 제목.
     */
    @Column(nullable = false)
    private String beforeTitle;

    /**
     * 게시물 ai 변환 후 제목.
     */
    @Column(nullable = false)
    private String afterTitle;

    /**
     * 게시물 내의 이미지. url로 저장.
     */
    @Column(length = 500)
    private String imageUrl;

    /**
     * 게시글 신고 유무. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isReported;

    /**
     * 게시글 삭제 유무. soft deleted이므로 삭제되어도 db에는 존재하게됩니다. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isDeleted;

    /**
     * 숨김 유무. 기본값 = 0
     */
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private boolean isHidden;

    /**
     * 자신의 게시물에 등록하는 반응. null일 경우(작성자가 선택하지 않았을 경우) ai가 글의 이모지를 선택해줍니다.
     */
    @Enumerated(EnumType.STRING)
    private Emotion emotionType;

    /**
     * 글의 컨셉. enum 타입으로 총 5가지 입니다.
     */
    @Enumerated(EnumType.STRING)
    private Concept conceptType;

    /**
     * Post 생성자.
     */
    private Post(Member author, String beforeContent, String afterContent, String beforeTitle,
            String afterTitle,
            String imageUrl, Emotion emotionType, Concept conceptType) {
        this.author = author;
        this.beforeContent = beforeContent;
        this.afterContent = afterContent;
        this.beforeTitle = beforeTitle;
        this.afterTitle = afterTitle;
        this.imageUrl = imageUrl;
        this.isReported = false;
        this.isDeleted = false;
        this.isHidden = false;
        this.emotionType = emotionType;
        this.conceptType = conceptType;
    }

    /**
     * Post 팩토리 메서드.
     */
    public static Post createPost(Member author, String beforeContent, String afterContent,
            String beforeTitle, String afterTitle, String imageUrl,
            Emotion emotionType,
            Concept conceptType) {
        return new Post(author, beforeContent, afterContent, beforeTitle, afterTitle, imageUrl,
                emotionType, conceptType);
    }

    /**
     * 게시글 삭제를 true로 전환.
     */
    public void delete() {
        this.isDeleted = true;
    }

    /**
     * 게시글 숨김을 true로 전환.
     */
    public void hide() {
        this.isHidden = true;
    }

    /**
     * 게시글 숨김을 false로 전환.
     */
    public void unhide() {
        this.isHidden = false;
    }

    /**
     * 게시글 삭제 버튼을 위한 boolean값.
     *
     * @param memberId 사용자 id
     * @return 작성자가 맞으면 true, 틀리면 false
     */
    public boolean isMine(Long memberId) {
        return memberId.equals(this.getAuthor().getId());
    }
}
