package today.sesac.versebyverse.post.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * 게시글 첨삭 에러 코드.
 */
@Getter
public enum PostProofreadErrorCode implements IErrorCode {
    POST_PROOFREAD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 첨삭입니다."),
    POST_PROOFREAD_ALREADY_PUBLISHED(HttpStatus.BAD_REQUEST, "이미 발행된 첨삭입니다."),
    POST_PROOFREAD_TASK_MISMATCH(HttpStatus.BAD_REQUEST, "첨삭의 작업이 일치하지 않습니다.");

    private final HttpStatus httpStatus;

    private final String message;

    PostProofreadErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}