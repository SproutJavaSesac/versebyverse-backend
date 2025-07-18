package today.sesac.versebyverse.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * 인증/인가 과정에서 사용되는 에러 코드입니다.
 */
@Getter
public enum AuthErrorCode implements IErrorCode {

    WITHDRAW_FAILURE(HttpStatus.BAD_REQUEST, "회원 탈퇴에 실패했습니다.");

    private final HttpStatus httpStatus;

    private final String message;

    AuthErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
