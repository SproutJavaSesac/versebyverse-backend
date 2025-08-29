package today.sesac.versebyverse.post.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 게시글 첨삭 예외 클래스.
 */
public class PostProofReadException extends AbstractBaseException {

    /**
     * 게시글 첨삭 예외를 처리하는 클래스.
     *
     * @param postProofReadErrorCode 첨삭 에러 코드
     * @param param                  추가적인 파라미터
     */
    public PostProofReadException(PostProofReadErrorCode postProofReadErrorCode, String param) {

        super(postProofReadErrorCode, param, postProofReadErrorCode.getMessage());
    }
}