package today.sesac.versebyverse.post.exception;

import today.sesac.versebyverse.global.exception.AbstractBaseException;

public class PostException extends AbstractBaseException {

    public PostException(PostErrorCode postErrorCode, String param) {

        super(postErrorCode, param, postErrorCode.getMessage());
    }
}