package today.sesac.shoutify.global.exception;

/**
 * 회원 권한이 필요한 경우(로그인해야 하는 경우) 발생하는 예외입니다.
 * 기본적으로 HTTP 상태 코드 401에 대응됩니다.
 * @see org.springframework.http.HttpStatus#UNAUTHORIZED
 */
public class LoginRequiredException extends AbstractBaseException {
	public LoginRequiredException(String param, String errorMessage) {
		super(GlobalErrorCode.LOGIN_REQUIRED, param, errorMessage);
	}
}
