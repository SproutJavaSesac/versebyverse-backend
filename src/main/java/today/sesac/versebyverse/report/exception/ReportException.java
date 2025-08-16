package today.sesac.versebyverse.report.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 신고 도메인에서 발생하는 예외를 처리하는 클래스입니다. 신고 관련 비즈니스 로직에서 발생할 수 있는 다양한 예외 상황을 처리합니다.
 */
public class ReportException extends AbstractBaseException {

    /**
     * ReportException을 생성합니다.
     *
     * @param reportErrorCode 신고 관련 에러 코드
     * @param param           에러 메시지에 포함될 파라미터
     */
    public ReportException(ReportErrorCode reportErrorCode, String param) {

        super(reportErrorCode, param, reportErrorCode.getMessage());
    }
}

