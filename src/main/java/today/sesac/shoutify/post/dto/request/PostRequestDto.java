package today.sesac.shoutify.post.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 클라이언트로부터 입력 받는 게시글입니다. 제목, 내용, 감정, 이미지를 입력 받습니다.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDto {

  private String title;
  private String content;
  private String emotionType;
  private MultipartFile image;

  /**
   * AI 욕설 순화, 감정 분석 등의 기능에 사용할 정적 팩토리 메서드입니다.
   *
   * @param title       게시글 제목
   * @param content     게시글 내용
   * @param emotionType 감정 유형
   * @return 이미지가 null 인 PostRequestDto 객체
   */
  public static PostRequestDto forSanitizedProfanity(String title, String content,
      String emotionType) {
    return new PostRequestDto(title, content, emotionType, null);
  }
}
