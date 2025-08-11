package today.sesac.versebyverse.report.entity;

/**
 * 신고 유형을 정의하는 열거형입니다. 게시글과 댓글 중 어떤 것을 신고하는지 구분합니다.
 */
public enum ReportType {
    /**
     * 게시글 신고를 나타냅니다.
     */
    POST,

    /**
     * 댓글 신고를 나타냅니다.
     */
    COMMENT
}
