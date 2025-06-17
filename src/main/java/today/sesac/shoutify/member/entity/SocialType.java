package today.sesac.shoutify.member.entity;

import lombok.Getter;

@Getter
public enum SocialType {

    KAKAO("카카오"),
    GOOGLE("구글");

    private final String type;

    SocialType(String type) {
        this.type = type;
    }
}
