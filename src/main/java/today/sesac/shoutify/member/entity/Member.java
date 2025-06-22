package today.sesac.shoutify.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	@NotNull
	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	// TODO: id 외에도 특정할 수 있는 필드 이름 정하기(이메일은? 이메일 이름은 같지만 소셜 로그인이 다른 경우 떄문에 안됨)
	// TODO: 현재는 email 저장 중, 이후 email 필드 따로 만들고 username에는 이메일에 몇 가지 고유 정보를 더해서 구분할 수 있게 만들 예정
	private String username;

	@NotNull
	@Column(length = 50)
	private String nickname;

	private Member(RoleType roleType, SocialType socialType, String username, String nickname) {
		this.roleType = roleType;
		this.socialType = socialType;
		this.username = username;
		this.nickname = nickname;
	}

	public static Member create(RoleType roleType, SocialType socialType, String username, String nickname) {
		return new Member(roleType, socialType, username, nickname);
	}
}
