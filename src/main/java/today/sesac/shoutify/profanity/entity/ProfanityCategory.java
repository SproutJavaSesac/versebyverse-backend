package today.sesac.shoutify.profanity.entity;

import lombok.Getter;

/**
 * 비속어의 카테고리를 정의하는 열거형(enum)입니다. 비속어의 성격과 용도에 따라 분류됩니다.
 */
@Getter
public enum ProfanityCategory {
    /**
     * 일반적인 욕설 표현. 예: "씨발", "좆", "병신" 등 명백한 비속어 단어들
     */
    GENERAL_SWEAR("일반 욕설"),

    /**
     * 성적인 내용을 포함한 발언 또는 비하 표현. 예: "보지", "자지", "섹스", "떡치다" 등
     */
    SEXUAL_DEGRADATION("성적 발언"),

    /**
     * 특정 집단, 인종, 성별, 지역 등을 차별하거나 혐오하는 표현. 예: "틀딱", "흑형", "급식충", "김치녀", "장애인 비하" 등
     */
    DISCRIMINATION_HATE("차별 혐오"),

    /**
     * 우회적, 조합형, 초성 등 변형된 비속어 표현 (비표준 욕설). 예: "ㅅㅂ", "시@팔", "fuxk" 등
     */
    MODIFIED_SWEAR("변형 욕설");

    private final String label;

    ProfanityCategory(String label) {
        this.label = label;
    }

}
