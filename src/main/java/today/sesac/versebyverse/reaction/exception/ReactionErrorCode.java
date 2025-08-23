package today.sesac.versebyverse.reaction.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * 반응하기 관련 예외 코드입니다.
 */
@Getter
public enum ReactionErrorCode implements IErrorCode {
    DUPLICATE_REACTION_FOUND(HttpStatus.CONFLICT, "이미 같은 감정 반응이 존재합니다.");

    private final HttpStatus httpStatus;

    private final String message;

    ReactionErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}
