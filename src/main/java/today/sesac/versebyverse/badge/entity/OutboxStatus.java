package today.sesac.versebyverse.badge.entity;

/**
 * 아웃박스의 상태 표시를 위한 Enum.
 */
public enum OutboxStatus {

    WAITING,
    FAILED,
    DELIVERED,
    DONE;
}
