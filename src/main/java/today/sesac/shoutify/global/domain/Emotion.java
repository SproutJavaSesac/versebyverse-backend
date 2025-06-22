package today.sesac.shoutify.global.domain;

import lombok.Getter;

/**
 * 게시물과 댓글, 자신의 감정을 표시하는 감정/반응하기 enum 입니다.
 */
@Getter
public enum Emotion {
    HAPPY("happy"),
    SAD("sad"),
    ANGRY("angry"),
    EXCITED("excited"),
    CONFUSED("confused"),
    PROUD("proud");

    private final String emotion;

    Emotion(String emotion) {
        this.emotion = emotion;
    }
}
