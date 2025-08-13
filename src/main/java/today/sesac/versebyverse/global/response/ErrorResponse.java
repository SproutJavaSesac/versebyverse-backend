package today.sesac.versebyverse.global.response;

import java.time.LocalDateTime;

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