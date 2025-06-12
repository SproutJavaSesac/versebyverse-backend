package today.sesac.shoutify.reaction.entity;

import lombok.Getter;

@Getter
public enum Emogi {
    HAPPY("happy"),
    SAD("sad"),
    ANGRY("angry"),
    EXCITED("excited"),
    CONFUSED("confused"),
    PROUD("proud")
    ;

    private final String emogi;

    Emogi(String emogi) {
        this.emogi = emogi;
    }

    public String getEmogi() {
        return emogi;
    }
}
