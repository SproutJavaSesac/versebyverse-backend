package today.sesac.versebyverse.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.member.entity.RoleType;

/**
 * 권한 변경 내역 DTO.
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class UpdateRoleResponseDto {

    private final RoleType roleType;
}
