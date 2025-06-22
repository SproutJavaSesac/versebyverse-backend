package today.sesac.shoutify.auth.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;

// TODO: 로그인 상태 확인용 임시 DTO - 삭제 예정
@Data
@AllArgsConstructor
public class CheckStatusResponse {

	private Long memberId;
	private RoleType roleType;
	private SocialType socialType;
	private String username;
}
