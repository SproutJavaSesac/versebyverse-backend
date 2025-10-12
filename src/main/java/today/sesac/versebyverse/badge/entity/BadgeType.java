package today.sesac.versebyverse.badge.entity;

import lombok.Getter;

/**
 * 배지 종류.
 */
@Getter
public enum BadgeType {

    FIRST_SIGNUP("새로운 가족"),

    // 게시글 관련
    FIRST_POST("첫 게시글"),
    NOVICE_WRITER("신진 작가"), // 게시글 10회 작성

    // 댓글 관련
    FIRST_COMMENT("소통의 시작"),
    REACTION_FAIRY("리액션 요정"),   // 댓글 5회 작성
    POPULAR_POST("인기 게시글"), // 작성한 게시글에 댓글 5개 달렸을 때

    // 반응하기 관련
    FIRST_REACTION("첫 반응하기"),
    FRIENDLY_NEIGHBOR("다정한 이웃"),    // 반응하기 10회 누를 때

    // 랭킹 관련
    FIRST_RANKING("첫 랭킹 진입");

    private final String badgeName;

    BadgeType(String badgeName) {

        this.badgeName = badgeName;
    }
}
