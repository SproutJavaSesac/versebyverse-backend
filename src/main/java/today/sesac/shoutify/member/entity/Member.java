package today.sesac.shoutify.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.shoutify.global.domain.BaseEntity;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotNull
    private String accountId;
    @NotNull
    private String password;
    @NotNull
    private int authority;
    private String nickname;
    private String email;

    private Member(
            String accountId,
            String password,
            int authority,
            String nickname,
            String email
    ) {
        this.accountId = accountId;
        this.password = password;
        this.authority = authority;
        this.nickname = nickname;
        this.email = email;
    }

    public static Member signup(
            String accountId,
            String password,
            int authority,
            String nickname,
            String email
    ) {
        return new Member(accountId, password, authority, nickname, email);
    }
}