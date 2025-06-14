package today.sesac.shoutify.reaction.entity;

import lombok.Getter;

/**
 * 게시물과 댓글에 부여되는 Emoji의 Enum 클래스입니다.
 */
@Getter
public enum Emoji {
    HAPPY("happy"),
    SAD("sad"),
    ANGRY("angry"),
    EXCITED("excited"),
    CONFUSED("confused"),
    PROUD("proud");

    private final String emoji;

    Emoji(String emoji) {
        this.emoji = emoji;
    }
}
