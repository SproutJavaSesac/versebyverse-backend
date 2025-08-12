package today.sesac.versebyverse.badge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.badge.repository.BadgeRepository;
import today.sesac.versebyverse.badge.repository.MemberBadgeRepository;

/**
 * CommentService는 댓글 관련 비즈니스 로직을 처리하는 서비스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;

    private final MemberBadgeRepository memberBadgeRepository;

}
