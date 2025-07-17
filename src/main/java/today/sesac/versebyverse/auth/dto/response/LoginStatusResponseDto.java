package today.sesac.versebyverse.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

// TODO: 로그인 상태 확인용 임시 DTO - 삭제 예정
@Data
@AllArgsConstructor
public class LoginStatusResponseDto {

    private Boolean isAuthenticated;

    private Long memberId;

    private String nickname;

    private String email;

}