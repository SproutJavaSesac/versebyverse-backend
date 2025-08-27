package today.sesac.versebyverse.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 상태 반환 DTO.
 */
@Getter
@AllArgsConstructor
public class LoginStatusResponseDto {

    private Boolean isAuthenticated;

    private Long memberId;

    private String nickname;

    private String email;

    /**
     * 사용자가 인증되지 않았을 경우의 DTO를 생성합니다. idAuthenticated만 false를 입력하고, 나머지 필드에는 null을 입력합니다.
     *
     * @return 로그인 상태 반환 DTO
     */
    public static LoginStatusResponseDto createUnauthenticated() {

        return new LoginStatusResponseDto(false, null, null, null);
    }

    /**
     * 사용자가 인증된 경우의 DTO를 생성합니다.
     *
     * @param memberId 사용자의 ID
     * @param nickname 사용자의 nickname
     * @param email    사용자의 email
     * @return 로그인 상태 반환 DTO.
     */
    public static LoginStatusResponseDto createAuthenticated(Long memberId, String nickname,
            String email) {

        return new LoginStatusResponseDto(true, memberId, nickname, email);
    }

}