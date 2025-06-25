package today.sesac.shoutify.member.exception;

import today.sesac.shoutify.global.exception.AbstractBaseException;

/**
 * TODO: 예외 종류 추가 및 수정, 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 설명 추가
 */
public class MemberAlreadyExistException extends AbstractBaseException {

    public MemberAlreadyExistException(String param, String errorMessage) {
        super(MemberErrorCode.MEMBER_ALREADY_EXIST, param, errorMessage);
    }
}
