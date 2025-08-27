package today.sesac.versebyverse.global.exception;

/**
 * 파일 업로드 관련 예외입니다.
 */
public class FileUploadException extends RuntimeException {

    public FileUploadException(String message) {

        super(message);
    }

    public FileUploadException(String message, Throwable cause) {

        super(message, cause);
    }
} 