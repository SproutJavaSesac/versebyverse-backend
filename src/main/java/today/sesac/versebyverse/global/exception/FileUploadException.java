package today.sesac.versebyverse.global.exception;

/**
 * 파일 업로드 관련 예외입니다.
 */
public class FileUploadException extends RuntimeException {

    // TODO: 도메인 분류 고려, 커스텀 예외 처리 하기
    public FileUploadException(String message) {

        super(message);
    }

    public FileUploadException(String message, Throwable cause) {

        super(message, cause);
    }
} 