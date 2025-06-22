package today.sesac.shoutify.ai.service;

/**
 * AI 기능 처리용 공통 인터페이스입니다.
 *
 * @param <T> 입력 DTO 타입
 * @param <R> 출력 DTO 타입
 */
public interface AiService<T, R> {

  /**
   * AI 처리를 수행하고 결과를 반환합니다.
   *
   * @param input 입력 데이터
   * @return 변환된 결과 데이터
   */
  public R refineProfanity(T input);
}
