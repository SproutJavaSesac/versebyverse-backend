package today.sesac.versebyverse.global.exception;

/**
 * 요청한 정보가 존재하지 않는 경우 발생하는 예외입니다. 기본적으로 HTTP 상태 코드 404에 대응됩니다. 특정 리소스에 대한 예외을 처리하기 위해 상속하여 사용합니다.
 *
 * @see org.springframework.http.HttpStatus#NOT_FOUND
 */
public abstract class AbstractUndiscoveredException extends AbstractBaseException {

    protected AbstractUndiscoveredException(String param, String errorMessage) {

        super(GlobalErrorCode.UNDISCOVERED, param, errorMessage);
    }
}