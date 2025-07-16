package today.sesac.versebyverse.global.exception;

import lombok.Getter;

/**
 * 어플리케이션에서 발생하는 예외를 처리하기 위한 기본 클래스입니다. 어플리케이션 예외를 명확하게 하기 위해 상속받아 사용합니다.
 */
@Getter
public abstract class AbstractBaseException extends RuntimeException {

    private final IErrorCode IErrorCode;

    private final String param;

    protected AbstractBaseException(IErrorCode IErrorCode, String param,
            String errorMessageInStackTrace) {

        super(errorMessageInStackTrace);
        this.IErrorCode = IErrorCode;
        this.param = param;
    }
}