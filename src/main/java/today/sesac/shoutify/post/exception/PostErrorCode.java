package today.sesac.shoutify.post.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.shoutify.global.exception.IErrorCode;

@Getter
public enum PostErrorCode implements IErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),
    INVALID_CONCEPT_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 컨셉 타입입니다."),
    INVALID_EMOTION_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 감정 타입입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    PostErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
