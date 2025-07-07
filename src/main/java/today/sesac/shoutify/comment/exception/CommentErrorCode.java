package today.sesac.shoutify.comment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.shoutify.global.exception.IErrorCode;

/**
 * 댓글 관련 예외 코드입니다.
 */
@Getter
public enum CommentErrorCode implements IErrorCode {
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),
    MAX_LEVEL_EXCEEDED(HttpStatus.BAD_REQUEST, "최대 댓글 레벨을 초과했습니다.");

    private final HttpStatus httpStatus;

    private final String message;

    CommentErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}
