package today.sesac.versebyverse.report.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * 신고 도메인에서 발생할 수 있는 에러 코드들을 정의하는 열거형입니다. 각 에러 상황에 대한 HTTP 상태 코드와 사용자 친화적인 메시지를 제공합니다.
 */
@Getter
public enum ReportErrorCode implements IErrorCode {
    /**
     * 신고 대상이 유효하지 않을 때 발생합니다. 게시글과 댓글 중 하나만 선택해야 합니다.
     */
    INVALID_REPORT_TARGET(HttpStatus.BAD_REQUEST, "게시글 또는 댓글 중 하나만 선택해야 합니다."),

    /**
     * 존재하지 않는 신고를 조회하려고 할 때 발생합니다.
     */
    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 신고입니다."),

    /**
     * 이미 신고한 대상에 대해 중복 신고를 시도할 때 발생합니다.
     */
    DUPLICATE_REPORT(HttpStatus.CONFLICT, "이미 신고한 대상입니다."),

    /**
     * 자신의 게시글이나 댓글을 신고하려고 할 때 발생합니다.
     */
    SELF_REPORT_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "자신의 게시글이나 댓글은 신고할 수 없습니다.");

    /**
     * HTTP 응답 상태 코드입니다.
     */
    private final HttpStatus httpStatus;

    /**
     * 사용자에게 표시될 에러 메시지입니다.
     */
    private final String message;

    /**
     * ReportErrorCode를 생성합니다.
     *
     * @param httpStatus HTTP 상태 코드
     * @param message    에러 메시지
     */
    ReportErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}