package today.sesac.versebyverse.badge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.BaseEntity;
import today.sesac.versebyverse.member.entity.Member;

/**
 * 회원이 배지를 소유하고 있는지 나타내는 엔티티.
 */
@Getter
@Entity
@Table(name = "member_badeges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberBadge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge;

    private MemberBadge(Member member, Badge badge) {
        this.member = member;
        this.badge = badge;
    }

    /**
     * 새로운 MemberBadge 엔티티를 생성합니다.
     *
     * @param member 배지를 획득한 회원
     * @param badge 배지
     * @return 새로운 배지 소유 여부 엔티티
     */
    public static MemberBadge create(Member member, Badge badge) {
        return new MemberBadge(member, badge);
    }
}
