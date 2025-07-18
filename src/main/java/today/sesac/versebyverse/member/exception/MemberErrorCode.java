package today.sesac.versebyverse.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import today.sesac.versebyverse.global.exception.IErrorCode;

/**
 * 회원 기능에서 사용하는 에러 코드입니다. 어플리케이션에서 사용하는 코드임을 구분하기 위해 기존 HttpStatus의 의도는 담지만, 상이한 단어를 사용합니다.
 */
@Getter
public enum MemberErrorCode implements IErrorCode {
    MEMBER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다.");

    private final HttpStatus httpStatus;

    private final String message;

    MemberErrorCode(HttpStatus httpStatus, String message) {

        this.httpStatus = httpStatus;
        this.message = message;
    }
}