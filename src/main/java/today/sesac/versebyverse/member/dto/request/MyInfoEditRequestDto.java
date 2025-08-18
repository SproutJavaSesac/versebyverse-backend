package today.sesac.versebyverse.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 내 정보 수정 요청 DTO.
 */
@Getter
@AllArgsConstructor
public class MyInfoEditRequestDto {

    @NotBlank
    @Size(max = 50, message = "닉네임은 50자 이하여야 합니다")
    private String nickname;
}