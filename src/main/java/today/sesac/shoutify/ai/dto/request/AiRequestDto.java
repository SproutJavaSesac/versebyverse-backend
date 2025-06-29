package today.sesac.shoutify.ai.dto.request;

import today.sesac.shoutify.ai.dto.response.AiResponseDto;

/**
 * AI 요청 데이터 전송 객체(DTO)의 추상 기본 클래스입니다.
 *
 * <p>AI 서비스에 전달할 요청 데이터를 담기 위한 공통 인터페이스를 제공합니다. 모든 AI 요청 DTO 클래스는 이 클래스를 상속받아야 합니다.
 * </p>
 *
 * <p>현재 구현된 요청 DTO:
 *     <ul>
 *         <li>{@link CommentAiRequestDto} - 댓글 AI 요청</li>
 *         <li>{@link PostAiRequestDto} - 게시글 AI 요청</li>
 *     </ul>
 * </p>
 *
 * @see CommentAiRequestDto
 * @see PostAiRequestDto
 */
public abstract class AiRequestDto<Res extends AiResponseDto> {

}
