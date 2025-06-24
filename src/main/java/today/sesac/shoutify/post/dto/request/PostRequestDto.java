package today.sesac.shoutify.post.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 클라이언트로부터 입력 받는 게시글입니다. 제목, 내용, 감정, 이미지를 입력 받습니다.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDto {

    private String title;
    private String conceptType;
    private String emotionType;
    private String content;
    private String imageUrl;

    /**
     * 모든 필드를 포함하여 PostRequestDto 객체를 생성하는 정적 팩토리 메서드입니다. 주로 클라이언트로부터 완전한 게시글 정보를 수신할 때 사용됩니다.
     *
     * @param title       게시글 제목
     * @param conceptType 게시글 컨셉 유형
     * @param emotionType 게시글 감정 유형
     * @param content     게시글 내용
     * @param imageUrl    게시글에 첨부된 이미지 URL
     * @return 모든 필드가 설정된 PostRequestDto 객체
     */
    public static PostRequestDto of(String title,
            String conceptType,
            String emotionType,
            String content,
            String imageUrl) {
        return new PostRequestDto(title, conceptType, emotionType, content, imageUrl);
    }
}
