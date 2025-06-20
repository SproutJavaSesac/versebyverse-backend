package today.sesac.shoutify.member.exception;

import today.sesac.shoutify.global.exception.AbstractBaseException;

public class MemberAlreadyExistException extends AbstractBaseException {
	public MemberAlreadyExistException(String param, String errorMessage) {
		super(MemberErrorCode.MEMBER_ALREADY_EXIST, param, errorMessage);
	}
}
