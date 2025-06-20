package today.sesac.shoutify.member.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import today.sesac.shoutify.global.exception.IErrorCode;

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
