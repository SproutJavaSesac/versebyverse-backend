package today.sesac.versebyverse.comment.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 댓글 관련 예외 클래스입니다.
 */
public class CommentException extends AbstractBaseException {

    /**
     * 에러 코드에 있는 메시지를 사용해 exception 생성합니다.
     *
     * @param commentErrorCode 댓글 관련 에러 코드
     * @param param            에러가 발생한 파라미터
     */
    public CommentException(CommentErrorCode commentErrorCode, String param) {

        super(commentErrorCode, param, commentErrorCode.getMessage());
    }

    /**
     * 에러 코드와 커스텀 메시지를 사용해 exception 생성합니다.
     *
     * @param commentErrorCode 댓글 관련 에러 코드
     * @param param            에러가 발생한 파라미터
     * @param message          예외 스택에 남길 메시지
     */
    public CommentException(CommentErrorCode commentErrorCode, String param, String message) {

        super(commentErrorCode, param, message);
    }
}