package today.sesac.versebyverse.ai.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * ai 관련 예외 클래스입니다.
 */
public class AiException extends AbstractBaseException {

    /**
     * 에러 코드에 있는 메시지를 사용해 exception 생성합니다.
     *
     * @param aiErrorCode ai 관련 에러 코드
     * @param param       에러가 발생한 파라미터
     */
    public AiException(AiErrorCode aiErrorCode, String param) {

        super(aiErrorCode, param, aiErrorCode.getMessage());
    }

    /**
     * 에러 코드와 커스텀 메시지를 사용해 exception 생성합니다.
     *
     * @param aiErrorCode ai 관련 에러 코드
     * @param param       에러가 발생한 파라미터
     * @param message     예외 스택에 남길 메시지
     */
    public AiException(AiErrorCode aiErrorCode, String param, String message) {

        super(aiErrorCode, param, message);
    }
}
