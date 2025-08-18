package today.sesac.versebyverse.report.entity;

/**
 * 신고 처리 상태를 정의하는 열거형입니다. 신고가 접수된 후 처리되는 과정을 추적할 수 있습니다.
 */
public enum StatusType {
    /**
     * 신고가 접수되어 처리 대기 중인 상태입니다.
     */
    PENDING,

    /**
     * 신고가 승인된 상태입니다.
     */
    ACCEPTED,

    /**
     * 신고가 거부된 상태입니다.
     */
    REJECTED,

    /**
     * 신고된 게시글이나 댓글이 이미 삭제된 상태입니다.
     */
    POSTPONE
}
