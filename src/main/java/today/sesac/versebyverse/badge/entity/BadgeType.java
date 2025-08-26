package today.sesac.versebyverse.badge.entity;

import lombok.Getter;

/**
 * 배지 종류.
 */
@Getter
public enum BadgeType {

    FIRST_POST("첫 게시글"),
    FIRST_SIGNUP("새로운 가족"),
    NOVICE_WRITER("신진 작가");

    private final String badgeName;

    BadgeType(String badgeName) {

        this.badgeName = badgeName;
    }
}
