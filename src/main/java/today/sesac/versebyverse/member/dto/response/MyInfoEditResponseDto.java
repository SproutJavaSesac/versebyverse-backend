package today.sesac.versebyverse.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 내 정보 수정 응답 DTO.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class MyInfoEditResponseDto {

    private Long memberId;

    private String nickname;

    private String email;

    private String profileImageUrl;
}