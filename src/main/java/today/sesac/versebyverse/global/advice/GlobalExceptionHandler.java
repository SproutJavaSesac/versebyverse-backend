package today.sesac.versebyverse.global.advice;

import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import today.sesac.versebyverse.global.exception.AbstractBaseException;
import today.sesac.versebyverse.global.exception.GlobalErrorCode;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.global.response.ErrorResponse;

/**
 * 모든 Controller에서 발생하는 예외를 처리하기 위한 클래스입니다.
 *
 * <p>기본적으로 에러를 던진 곳에서 로그를 남기며,
 * 국제화된 에러 메시지를 제공합니다.</p>
 */
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    /**
     * 비즈니스 로직에서 발생하는 AbstractBaseException을 처리합니다.
     *
     * <p>HTTP 상태 코드는 AbstractBaseException에서 정의된 IErrorCode의 상태 코드로 반환됩니다.</p>
     *
     * @param exception AbstractBaseException 구현 예외
     * @return {@link ApiResponse} 객체
     */
    @ExceptionHandler(AbstractBaseException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleBaseException(
            AbstractBaseException exception) {

        log.warn("[BaseException] {} - {}", exception.getErrorCode().name(),
                exception.getMessage());

        ApiResponse<ErrorResponse> errorResponse = ApiResponse.fail(
                new ErrorResponse(
                        exception.getErrorCode().name(),
                        exception.getErrorCode().getMessage(),
                        exception.getParam()
                )
        );

        return ResponseEntity
                .status(exception.getErrorCode().getHttpStatus())
                .body(errorResponse);
    }

    /**
     * JSON 파싱 오류나 HTTP 메시지 읽기 실패 시 발생하는 예외를 처리합니다.
     *
     * <p>주로 다음과 같은 경우에 발생합니다:</p>
     * <ul>
     *   <li>잘못된 JSON 형식</li>
     *   <li>enum 타입 필드에 잘못된 문자열이 들어온 경우</li>
     *   <li>타입 변환 실패 (문자열 → 숫자, 날짜 등)</li>
     * </ul>
     *
     * @param ex      JSON 파싱 실패로 인해 발생한 예외
     * @param headers 응답에 작성될 헤더
     * @param status  HTTP 상태 코드
     * @param request 현재 요청
     * @return 400 Bad Request 응답과 함께 반환할 오류 정보
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        log.warn("[HttpMessageNotReadableException] : {}", ex.getMessage());

        ApiResponse<ErrorResponse> errorResponse = ApiResponse.fail(
                new ErrorResponse(
                        GlobalErrorCode.INVALID_REQUEST.name(),
                        resolveLocalizedMessage(GlobalErrorCode.INVALID_REQUEST.name(), request),
                        null
                )
        );

        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * 예상하지 못한 RuntimeException을 처리합니다.
     *
     * @param exception 예상하지 못한 RuntimeException
     * @param request   현재 요청
     * @return {@link ApiResponse} 객체
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<ErrorResponse> handleRuntimeException(
            RuntimeException exception, WebRequest request) {

        log.error("[RuntimeException] : ", exception);

        return ApiResponse.fail(
                new ErrorResponse(
                        GlobalErrorCode.INTERNAL_SERVER.name(),
                        resolveLocalizedMessage(GlobalErrorCode.INTERNAL_SERVER.name(), request),
                        null
                )
        );
    }

    /**
     * {@link MethodArgumentNotValidException} 처리를 커스터마이징합니다.
     *
     * <p>이 메서드는 {@link #handleExceptionInternal}에 위임합니다.</p>
     *
     * @param ex      처리할 예외
     * @param headers 응답에 작성될 헤더
     * @param status  선택된 응답 상태
     * @param request 현재 요청
     * @return 사용할 응답을 위한 {@code ResponseEntity}, 응답이 이미 커밋된 경우 {@code null}일 수 있음
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        log.warn("[MethodArgumentNotValidException] : {}", ex.getMessage());

        List<ErrorResponse> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        new ErrorResponse(
                                GlobalErrorCode.INVALID_REQUEST.name(),
                                fieldError.getDefaultMessage(),
                                fieldError.getField()
                        ))
                .toList();

        return ResponseEntity.badRequest().body(ApiResponse.fail(errorList));
    }

    /**
     * 이 클래스의 다른 모든 메서드들이 위임하는 내부 핸들러 메서드입니다. 공통 처리 및 {@link ResponseEntity} 생성을 담당합니다.
     *
     * <p>Spring이 자동으로 처리하는 표준 예외들에 대한 최종 핸들러입니다.</p>
     *
     * @param ex         처리할 예외
     * @param body       응답에 사용할 본문
     * @param headers    응답에 사용할 헤더
     * @param statusCode 응답에 사용할 상태 코드
     * @param request    현재 요청
     * @return 사용할 응답을 위한 {@code ResponseEntity}, 응답이 이미 커밋된 경우 {@code null}일 수 있음
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request) {

        // 예외 타입별 에러 코드 매핑
        String errorCode = mapExceptionToErrorCode(ex);

        return ResponseEntity
                .status(statusCode)
                .body(ApiResponse.fail(
                        new ErrorResponse(
                                errorCode,
                                resolveLocalizedMessage(errorCode, request),
                                null
                        )
                ));
    }

    /**
     * 예외 타입을 구조화된 에러 코드로 매핑합니다.
     *
     * @param ex 발생한 예외
     * @return 구조화된 에러 코드
     */
    private String mapExceptionToErrorCode(Exception ex) {

        String className = ex.getClass().getSimpleName();

        return switch (className) {
            case "NoHandlerFoundException" -> "NOT_FOUND";
            case "NoResourceFoundException" -> "RESOURCE_NOT_FOUND";
            case "HttpRequestMethodNotSupportedException" -> "METHOD_NOT_ALLOWED";
            case "HttpMediaTypeNotSupportedException" -> "UNSUPPORTED_MEDIA_TYPE";
            case "HttpMediaTypeNotAcceptableException" -> "NOT_ACCEPTABLE";
            case "MissingPathVariableException" -> "MISSING_PATH_VARIABLE";
            case "MissingServletRequestParameterException" -> "MISSING_PARAMETER";
            case "MissingServletRequestPartException" -> "MISSING_REQUEST_PART";
            case "ServletRequestBindingException" -> "REQUEST_BINDING_ERROR";
            case "HandlerMethodValidationException" -> "METHOD_VALIDATION_FAILED";
            case "MaxUploadSizeExceededException" -> "FILE_SIZE_EXCEEDED";
            case "ErrorResponseException" -> "RESPONSE_ERROR";
            default -> "INTERNAL_SERVER_ERROR"; // 기본값
        };
    }

    /**
     * 클라이언트의 로케일에 따라 국제화된 에러 메시지를 해결합니다.
     *
     * @param errorCode 에러 코드
     * @param request   클라이언트 요청 (로케일 정보 포함)
     * @return 국제화된 에러 메시지 또는 기본 메시지
     */
    private String resolveLocalizedMessage(String errorCode, WebRequest request) {

        Locale locale = request.getLocale();
        String messageKey = "error." + errorCode;

        try {
            return messageSource.getMessage(messageKey, null, locale);
        } catch (NoSuchMessageException e) {
            // 메시지가 없으면 에러 코드를 그대로 반환
            log.warn("No message found for error code: {} with locale: {}", errorCode, locale);
            return errorCode;
        }
    }
}