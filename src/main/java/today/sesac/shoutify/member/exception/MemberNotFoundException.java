package today.sesac.shoutify.member.exception;

import today.sesac.shoutify.global.exception.AbstractBaseException;

public class MemberNotFoundException extends AbstractBaseException {

    public MemberNotFoundException(String param, String errorMessage) {
        super(MemberErrorCode.MEMBER_NOT_FOUND, param, errorMessage);
    }
}
