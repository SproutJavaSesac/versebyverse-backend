package today.sesac.shoutify.member.entity;

import jakarta.persistence.*;
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
    @Column(columnDefinition = "TINYINT(1)")
    private boolean role;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @NotNull
    @Column(length = 50)
    private String nickname;

    private Member(boolean role, SocialType socialType, String nickname) {
        this.role = role;
        this.socialType = socialType;
        this.nickname = nickname;
    }

    public static Member create(boolean role, SocialType socialType, String nickname) {
        return new Member(role, socialType, nickname);
    }
}
