package today.sesac.versebyverse.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginStatusResponseDto {

    private Boolean isAuthenticated;

    private Long memberId;

    private String nickname;

    private String email;

    // 사용자가 인증되지 않았을 경우
    public static LoginStatusResponseDto createUnauthenticated() {
        return new LoginStatusResponseDto(false, null, null, null);
    }

    // 사용자가 인증되었을 경우
    public static LoginStatusResponseDto createAuthenticated(Long memberId, String nickname, String email) {
        return new LoginStatusResponseDto(true, memberId, nickname, email);
    }

}