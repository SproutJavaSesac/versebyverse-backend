package today.sesac.shoutify.global.exception;

import org.springframework.http.HttpStatus;

/**
 * ENUM으로 ErrorCode 유형을 관리하기 위한 에러 코드 인터페이스입니다.
 * name, message, httpStatus로 각각 고유 에러 코드, 에러 메시지, http 상태 코드를 관리합니다.
 */
public interface IErrorCode {
	String name();

	String getMessage();

	HttpStatus getHttpStatus();
}
