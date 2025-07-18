package today.sesac.versebyverse.auth.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 회원 탈퇴 과정에서 발생하는 예외를 처리하기 위한 클래스입니다. 구글 토큰 반환 오류 등에서 발생할 수 있습니다.
 */
public class WithdrawFailureException extends AbstractBaseException {

    public WithdrawFailureException(String param, String errorMessage) {
        super(AuthErrorCode.WITHDRAW_FAILURE, param, errorMessage);
    }
}
