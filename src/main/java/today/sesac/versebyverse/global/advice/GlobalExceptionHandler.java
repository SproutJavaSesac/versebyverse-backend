package today.sesac.versebyverse.global.advice;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import today.sesac.versebyverse.global.exception.AbstractBaseException;
import today.sesac.versebyverse.global.exception.GlobalErrorCode;
import today.sesac.versebyverse.global.response.ApiResponse;

/**
 * 모든 Controller 에서 발생하는 예외를 처리하기 위한 클래스입니다. 기본적으로 에러를 던진 곳에서 로그를 남깁니다.
 */
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 예상하지 못한 RuntimeException을 처리합니다.
     *
     * @param exception 예상하지 못한 RuntimeException
     * @return {@link ApiResponse} 객체
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(exception = RuntimeException.class)
    public ApiResponse<ErrorResponse> handleRuntimeException(RuntimeException exception) {

        log.error("[RuntimeException] : ", exception);
        return ApiResponse.fail(
                new ErrorResponse(
                        GlobalErrorCode.INTERNAL_SERVER.name(),
                        GlobalErrorCode.INTERNAL_SERVER.getMessage(),
                        null
                )
        );
    }

    /**
     * AbstractBaseException을 상속받은 예외를 처리합니다. http 상태 코드는 AbstractBaseException에서 정의된 IErrorCode의 상태
     * 코드로 반환됩니다.
     *
     * @param exception AbstractBaseException 구현 예외
     * @return {@link ApiResponse} 객체
     */
    @ExceptionHandler(exception = AbstractBaseException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleBaseException(
            AbstractBaseException exception) {

        log.warn("[BaseException] : ", exception);
        ApiResponse<ErrorResponse> errorResponseApiResponse = ApiResponse.fail(
                new ErrorResponse(
                        exception.getIErrorCode().name(),
                        exception.getIErrorCode().getMessage(),
                        exception.getParam()
                )
        );
        return ResponseEntity
                .status(exception.getIErrorCode().getHttpStatus())
                .body(errorResponseApiResponse);
    }

    /**
     * 요청한 url이 존재하지 않는 경우 발생하는 예외를 처리합니다. 기본적으로 HTTP 상태 코드 404에 대응됩니다.
     *
     * @param exception NoHandlerFoundException 예외
     * @return {@link ApiResponse} 객체
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(exception = NoHandlerFoundException.class)
    public ApiResponse<ErrorResponse> handleNoHandlerFoundException(
            NoHandlerFoundException exception) {

        log.warn("[NoHandlerFoundException] : ", exception);
        return ApiResponse.fail(
                new ErrorResponse(
                        GlobalErrorCode.UNDISCOVERED.name(),
                        GlobalErrorCode.UNDISCOVERED.getMessage(),
                        null
                )
        );
    }

    /**
     * &#064;Valid  어노테이션을 사용하여 검증할 때 발생하는 예외를 처리합니다. 여러 개의 필드에서 에러가 발생할 수 있기에 List로 반환합니다.
     *
     * @param exception MethodArgumentNotValidException
     * @return {@link ApiResponse} 객체
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ApiResponse<List<ErrorResponse>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        log.warn("[MethodArgumentNotValidException] : ", exception);
        List<ErrorResponse> errorList = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        new ErrorResponse(
                                GlobalErrorCode.INVALID_REQUEST.name(),
                                fieldError.getDefaultMessage(),
                                fieldError.getField()
                        ))
                .toList();
        return ApiResponse.fail(errorList);
    }

    /**
     * {@link HttpMessageNotReadableException} 발생 시의 예외를 처리합니다.
     *
     * <p>주로 JSON → DTO 변환 중 발생하는 에러로, 다음과 같은 경우에 발생합니다:
     * <ul>
     *   <li>enum 타입 필드에 잘못된 문자열이 들어온 경우</li>
     *   <li>빈 문자열을 숫자, 날짜 등으로 변환하려는 경우</li>
     *   <li>잘못된 JSON 문법 (예: 쉼표 누락, 중괄호 미완성 등)</li>
     * </ul>
     * </p>
     * <p>
     * 예외 발생 시 400 Bad Request 응답을 반환하며, 오류 메시지는 {@link GlobalErrorCode#INVALID_REQUEST}를 따릅니다.
     *
     * @param exception 변환 실패로 인해 발생한 예외
     * @return 400 응답과 함께 반환할 오류 정보
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<ErrorResponse> handleEnumConversionException(
            HttpMessageNotReadableException exception) {

        log.warn("[HttpMessageNotReadableException] : ", exception);
        return ApiResponse.fail(
                new ErrorResponse(
                        GlobalErrorCode.INVALID_REQUEST.name(),
                        "요청 값이 올바르지 않습니다.",
                        null
                )
        );
    }

    /**
     * API 요청 실패 시 사용자에게 알려줄 오류 정보를 담습니다.
     */
    public record ErrorResponse(
            // 오류를 식별할 수 있는 오류 이름
            String name,
            // 오류에 대한 설명
            String message,
            // 사용자가 보낸 내용 중 오류가 발생한 파라미터
            String param,
            // 오류가 발생한 시각
            LocalDateTime timestamp
    ) {

        /**
         * timestamp를 현재 시각으로 설정하는 생성자입니다.
         *
         * @param name    오류 이름
         * @param message 오류 메시지
         * @param param   오류가 발생한 파라미터
         */
        public ErrorResponse(String name, String message, String param) {

            this(name, message, param, LocalDateTime.now());
        }
    }
}