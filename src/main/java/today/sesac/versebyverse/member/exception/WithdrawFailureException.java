package today.sesac.versebyverse.member.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 회원 탈퇴 과정에서 발생하는 예외를 처리하기 위한 클래스입니다. 구글 토큰 반환 오류 등에서 발생할 수 있습니다.
 */
public class WithdrawFailureException extends AbstractBaseException {

    /**
     * 에러 코드와 커스텀 메시지를 사용해 exception 생성합니다.
     *
     * @param param 에러가 발생한 파라미터
     * @param errorMessage 예외 스택에 남길 메시지
     */
    public WithdrawFailureException(String param, String errorMessage) {
        super(MemberErrorCode.WITHDRAW_FAILURE, param, errorMessage);
    }
}
