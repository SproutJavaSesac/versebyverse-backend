package today.sesac.versebyverse.global.exception;

/**
 * 현재 활성화된 profile(ex. local, prod)과 필요한 profile이 다를 때 발생하는 예외입니다. 주로 개발 환경에서만 작동하는 기능을 만들 때 사용합니다.
 */
public class InvalidProfileException extends AbstractBaseException {

    /**
     * 커스텀 메시지를 사용해 exception 생성합니다.
     *
     * @param param        예외가 발생한 파라미터
     * @param errorMessage 예외 스택에 남길 메시지
     */
    public InvalidProfileException(String param, String errorMessage) {

        super(GlobalErrorCode.INVALID_PROFILE, param, errorMessage);
    }
}
