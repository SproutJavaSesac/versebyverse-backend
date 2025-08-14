package today.sesac.versebyverse.badge.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.badge.entity.Badge;
import today.sesac.versebyverse.badge.entity.MemberBadge;
import today.sesac.versebyverse.badge.repository.BadgeRepository;
import today.sesac.versebyverse.badge.repository.MemberBadgeRepository;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * BadgeService는 배지 관련 비즈니스 로직을 처리하는 서비스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeService {

    private final BadgeRepository badgeRepository;

    private final MemberBadgeRepository memberBadgeRepository;

    private final PostRepository postRepository;

    /**
     * 게시글이 작성되었을 때, 조건을 만족하는 배지가 있으면 획득하는 메서드.
     *
     * @param author 게시글 작성자 객체.
     */
    public void checkAndGrantPostBadges(Member author) {

        List<MemberBadge> memberBadgeList = memberBadgeRepository.findByMemberId(author.getId());

        checkAndGrantFirstPostBadge(memberBadgeList, author);
        checkAndGrantTenthPostBadge(memberBadgeList, author);
    }

    private void checkAndGrantFirstPostBadge(List<MemberBadge> memberBadgeList, Member author) {

        Long authorId = author.getId();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals("첫 게시글"));

        long postCount = postRepository.countByAuthorIdAndIsDeletedFalse(authorId);

        if (postCount > 0 && !hasTargetBadge) {
            Badge badge = badgeRepository.findByName("첫 게시글")
                    .orElseThrow(() -> new RuntimeException("첫 게시글 배지를 찾을 수 없습니다"));

            MemberBadge memberBadge = MemberBadge.create(author, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

    private void checkAndGrantTenthPostBadge(List<MemberBadge> memberBadgeList, Member author) {

        Long authorId = author.getId();

        boolean hasTargetBadge = memberBadgeList.stream()
                .anyMatch(memberBadge -> memberBadge.getBadge().getName().equals("활발한 작가"));

        long postCount = postRepository.countByAuthorIdAndIsDeletedFalse(authorId);

        if (postCount >= 10 && !hasTargetBadge) {
            log.info("활발한 작가");
            Badge badge = badgeRepository.findByName("활발한 작가")
                    .orElseThrow(() -> new RuntimeException("error"));

            MemberBadge memberBadge = MemberBadge.create(author, badge);
            memberBadgeRepository.save(memberBadge);
        }
    }

}
