package today.sesac.versebyverse.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 로그인 등 전역에서 공통으로 사용하는 에러 코드입니다. 어플리케이션에서 사용하는 코드임을 구분하기 위해 기존 HttpStatus의 의도는 담지만, 상이한 단어를 사용합니다.
 */
@Getter
public enum GlobalErrorCode implements IErrorCode {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "유효하지 않은 형식입니다. 요청 값을 다시 확인해 주세요."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    PERMISSION_REQUIRED(HttpStatus.FORBIDDEN, "권한이 없습니다. 다시 확인해 주세요."),
    UNDISCOVERED(HttpStatus.NOT_FOUND, "요청하신 정보를 찾을 수 없습니다."),
    INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 문제가 발생했습니다. 잠시 후 다시 시도해 주세요.");

    private final HttpStatus httpStatus;

    private final String message;

    GlobalErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}