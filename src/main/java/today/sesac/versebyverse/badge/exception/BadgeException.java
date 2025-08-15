package today.sesac.versebyverse.badge.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 배지 관련 예외 클래스입니다.
 */
public class BadgeException extends AbstractBaseException {

    /**
     * 에러 코드에 있는 메시지를 사용해 exception 생성합니다.
     *
     * @param badgeErrorCode 댓글 관련 에러 코드
     * @param param 에러가 발생한 파라미터
     */
    public BadgeException(BadgeErrorCode badgeErrorCode, String param) {

        super(badgeErrorCode, param, badgeErrorCode.getMessage());
    }
}
