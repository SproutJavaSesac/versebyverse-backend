package today.sesac.versebyverse.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;

/**
 * 회원을 관리하는 도메인입니다.
 */
@Entity
@Getter
@Table(name = "members",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"social_type", "email"})
        })
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

    @NotNull
    private String email;

    @NotNull
    @Column(length = 50)
    private String nickname;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDeleted;

    private String profileImageUrl;

    private Member(RoleType roleType, SocialType socialType, String email, String nickname,
            String profileImageUrl) {

        this.roleType = roleType;
        this.socialType = socialType;
        this.email = email;
        this.nickname = nickname;
        this.isDeleted = false;
        this.profileImageUrl = profileImageUrl;
    }

    /**
     * 회원 가입합니다.
     * todo @prac2317 프로필 이미지 url 현재는 Null로 설정, 추후 받는 걸로 변경 필요
     *
     * @param roleType   역할
     * @param socialType 소셜 타입
     * @param email      가입한 이메일
     * @param nickname   닉네임
     * @return 생성된 회원 엔티티
     */
    public static Member create(RoleType roleType, SocialType socialType, String email,
            String nickname) {

        return new Member(roleType, socialType, email, nickname, null);
    }

    /**
     * 사용자의 프로필을 수정합니다.
     *
     * @param nickname 사용자의 닉네임
     */
    public void editProfile(String nickname) {

        this.nickname = nickname;
    }

    /**
     * 사용자의 권한을 변경합니다.
     *
     * @param roleType 사용자의 권한
     */
    public void updateRoleType(RoleType roleType) {

        this.roleType = roleType;
    }

    public void delete() {

        this.isDeleted = true;
    }
}