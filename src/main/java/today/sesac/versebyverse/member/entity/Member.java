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

    private Member(RoleType roleType, SocialType socialType, String email, String nickname) {

        this.roleType = roleType;
        this.socialType = socialType;
        this.email = email;
        this.nickname = nickname;
    }

    public static Member create(RoleType roleType, SocialType socialType, String email,
            String nickname) {

        return new Member(roleType, socialType, email, nickname);
    }

    public void editProfile(String nickname) {

        this.nickname = nickname;
    }
}