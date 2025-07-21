package today.sesac.versebyverse.profanity.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * 비속어 관련 예외 코드입니다.
 */
@Getter
public enum ProfanityErrorCode implements IErrorCode {
    PROFANITY_NOR_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 비속어입니다."),
    PROFANITY_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 등록된 비속어입니다."),
    PROFANITY_INVALID_CATEGORY(HttpStatus.BAD_REQUEST, "비속어 카테고리가 유효하지 않습니다.");

    private final HttpStatus httpStatus;

    private final String message;

    ProfanityErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }

}
