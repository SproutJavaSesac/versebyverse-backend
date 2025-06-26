package today.sesac.shoutify.post.exception;

import today.sesac.shoutify.global.exception.AbstractBaseException;

public class PostException extends AbstractBaseException {
    public PostException(PostErrorCode postErrorCode, String param) {
        super(postErrorCode, param, postErrorCode.getMessage());
    }
}
