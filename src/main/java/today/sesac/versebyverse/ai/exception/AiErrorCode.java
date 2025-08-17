package today.sesac.versebyverse.ai.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * AI 관련 예외 코드입니다.
 */
@Getter
public enum AiErrorCode implements IErrorCode {
    REQUIRED_FIELD_MISSING(HttpStatus.BAD_REQUEST, "필수 필드가 누락되었습니다.");

    private final HttpStatus httpStatus;

    private final String message;

    AiErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}
