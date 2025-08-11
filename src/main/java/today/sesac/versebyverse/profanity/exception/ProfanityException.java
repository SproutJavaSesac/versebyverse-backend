package today.sesac.versebyverse.profanity.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 비속어 관련 예외 코드입니다.
 */
public class ProfanityException extends AbstractBaseException {

    /**
     * 에러 코드에 있는 메시지를 사용해 exception 생성합니다.
     *
     * @param profanityErrorCode 비속어 관련 에러 코드
     * @param param              에러가 발생한 파라미터
     */
    public ProfanityException(ProfanityErrorCode profanityErrorCode, String param) {

        super(profanityErrorCode, param, profanityErrorCode.getMessage());
    }

    /**
     * 에러 코드와 커스텀 메시지를 사용해 exception 생성합니다.
     *
     * @param profanityErrorCode 비속어 관련 에러 코드
     * @param param              에러가 발생한 파라미터
     * @param message            예외 스택에 남길 메시지
     */
    public ProfanityException(ProfanityErrorCode profanityErrorCode, String param, String message) {

        super(profanityErrorCode, param, message);
    }

}

