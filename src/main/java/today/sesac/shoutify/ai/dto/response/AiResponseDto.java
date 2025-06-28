package today.sesac.shoutify.ai.dto.response;

/**
 * AI 응답 데이터 전송 객체(DTO)의 추상 기본 클래스입니다.
 *
 * <p>AI 서비스에서 생성된 응답 데이터를 전달하기 위한 공통 인터페이스를 제공합니다. 모든 AI 응답 DTO 클래스는 이 클래스를 상속받아야 합니다.
 * </p>
 *
 * <p>현재 구현된 응답 DTO:
 *     <ul>
 *         <li>{@link CommentAiResponseDto} - 댓글 AI 응답</li>
 *         <li>{@link PostAiResponseDto} - 게시글 AI 응답</li>
 *     </ul>
 * </p>
 *
 * @see CommentAiResponseDto
 * @see PostAiResponseDto
 */
public abstract class AiResponseDto {

}
