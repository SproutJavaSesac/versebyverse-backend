package today.sesac.versebyverse.badge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * 배지 관련 예외 코드입니다.
 */
@Getter
public enum BadgeErrorCode implements IErrorCode {

    BADGE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 배지입니다.");

    private final HttpStatus httpStatus;

    private final String message;

    BadgeErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}
