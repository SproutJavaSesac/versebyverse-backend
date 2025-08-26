package today.sesac.versebyverse.global.exception;

import lombok.Getter;

/**
 * 어플리케이션에서 발생하는 예외를 처리하기 위한 기본 클래스입니다. 어플리케이션 예외를 명확하게 하기 위해 상속받아 사용합니다.
 */
@Getter
public abstract class AbstractBaseException extends RuntimeException {

    private final IErrorCode errorCode;

    private final String param;

    /**
     * AbstractBaseException 생성자. 어플리케이션 예외를 처리하기 위해 IErrorCode와 파라미터를 받습니다.
     *
     * @param errorCode                어플리케이션 예외 코드
     * @param paramName                오류가 발생한 파라미터 변수명. 예: postId, commentId 등
     * @param errorMessageInStackTrace 오류 메시지. 스택 트레이스에 표시될 메시지입니다.
     */
    protected AbstractBaseException(IErrorCode errorCode, String paramName,
            String errorMessageInStackTrace) {

        super(errorMessageInStackTrace);
        this.errorCode = errorCode;
        this.param = paramName;
    }
}