package today.sesac.versebyverse.badge.entity;

import lombok.Getter;
import today.sesac.versebyverse.member.entity.Member;

/**
 * 배지 종류.
 */
@Getter
public enum BadgeType {

    FIRST_POST("첫 글 작성") {
        @Override
        public boolean check(Member member) {
            return false;
        }
    },
    POPULAR_WRITER("인기 작가") {
        @Override
        public boolean check(Member member) {
            return false;
        }
    },
    ACTIVE_WRITER("활발한 작가") {
        @Override
        public boolean check(Member member) {
            return false;
        }
    };

    private final String badgeName;

    /**
     * 회원의 배지 획득 조건을 확인합니다.
     *
     * @param member 회원
     * @return 회원의 배지 획득 가능 여부 반환
     */
    public abstract boolean check(Member member);

    BadgeType(String badgeName) {
        this.badgeName = badgeName;
    }
}
