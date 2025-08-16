package today.sesac.versebyverse.report.entity;

/**
 * 신고 사유를 정의하는 열거형입니다. 사용자가 신고할 수 있는 다양한 사유들을 포함합니다.
 */
public enum ReasonType {
    /**
     * 스팸 또는 광고성 내용입니다.
     */
    SPAM,

    /**
     * 부적절한 컨텐츠입니다.
     */
    INAPPROPRIATE_CONTENT,

    /**
     * 음란하거나 성적인 내용입니다.
     */
    PORNOGRAPHIC_CONTENT,

    /**
     * 불법 촬영 또는 도촬 관련 내용입니다.
     */
    ILLEGAL_FILMING,

    /**
     * 기타 위에 명시되지 않은 사유입니다.
     */
    OTHER
}
