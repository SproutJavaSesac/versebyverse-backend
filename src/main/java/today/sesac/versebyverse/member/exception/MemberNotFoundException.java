package today.sesac.versebyverse.member.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

public class MemberNotFoundException extends AbstractBaseException {

    public MemberNotFoundException(String param, String errorMessage) {

        super(MemberErrorCode.MEMBER_NOT_FOUND, param, errorMessage);
    }
}