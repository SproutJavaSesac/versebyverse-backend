package today.sesac.versebyverse.reaction.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

/**
 * 반응하기 관련 예외 클래스입니다.
 */
public class ReactionException extends AbstractBaseException {

    /**
     * 에러코드 메세지를 활용해 exception을 생성합니다.
     *
     * @param reactionErrorCode 에러코드
     * @param param             에러 발생 파라미터
     */
    public ReactionException(ReactionErrorCode reactionErrorCode, String param) {

        super(reactionErrorCode, param, reactionErrorCode.getMessage());
    }

}
