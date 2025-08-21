package today.sesac.versebyverse.member.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.auth.service.SocialLoginService;
import today.sesac.versebyverse.badge.entity.MemberBadge;
import today.sesac.versebyverse.badge.repository.MemberBadgeRepository;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.comment.repository.CommentRepository;
import today.sesac.versebyverse.global.event.MemberCreatedEvent;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.member.dto.response.MyBadgeListResponseDto;
import today.sesac.versebyverse.member.dto.response.MyCommentListResponseDto;
import today.sesac.versebyverse.member.dto.response.MyCommentSummaryDto;
import today.sesac.versebyverse.member.dto.response.MyInfoEditResponseDto;
import today.sesac.versebyverse.member.dto.response.MyInfoGetResponseDto;
import today.sesac.versebyverse.member.dto.response.MyPostListResponseDto;
import today.sesac.versebyverse.member.dto.response.MyPostSummaryDto;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.RoleType;
import today.sesac.versebyverse.member.entity.SocialType;
import today.sesac.versebyverse.member.exception.MemberNotFoundException;
import today.sesac.versebyverse.member.repository.MemberRepository;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스.
 * TODO: 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 설명 추가.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final SocialLoginService socialLoginService;

    private final MemberBadgeRepository memberBadgeRepository;

    private final ApplicationEventPublisher eventPublisher;

    /**
     * TODO: 서비스와 나머지(ex.controller) 사이도 DTO로 통신하기? return값 엔티티 그대로 말고 다른 방식으로 결정하기. 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 설명 추가
     */
    @Transactional
    public Member createMember(RoleType roleType, SocialType socialType, String email,
            String nickname) {

        Member member = Member.create(roleType, socialType, email, nickname);
        Member savedMember = memberRepository.save(member);

        eventPublisher.publishEvent(new MemberCreatedEvent(savedMember));

        return savedMember;
    }

    /**
     *  회원을 삭제합니다.
     *  soft-delete 방식으로 isDeleted 필드만 변경합니다.
     *
     * @param memberId 회원의 id
     */
    private void deleteMember(Long memberId) {
        log.info("회원 삭제 시작, memberId: {}", memberId);

        Member member = memberRepository.findByIdAndIsDeletedFalse(memberId).orElseThrow(
                () -> new MemberNotFoundException(null, String.format(
                        "해당 id를 가진 회원을 찾을 수 없습니다. memberId: %d", memberId)));

        member.delete();

        log.info("회원 삭제 완료, memberId: {}", memberId);
    }

    /**
     * 탈퇴 요청을 수행합니다.
     *
     * @param username 사용자를 구분하기 위해 사용되는 이름입니다.
     */
    @Transactional
    public void withdraw(Long memberId, String username) {

        log.info("회원 탈퇴 시작, memberId: {}, username: {}", memberId, username);

        // 1. DB에서 회원 삭제
        deleteMember(memberId);

        // 2. 소셜 로그인 연동 해제 API 호출
        socialLoginService.revokeAccess(username);

        log.info("회원 탈퇴 완료, memberId: {}, username: {}", memberId, username);
    }


    /**
     * 회원의 id로 db에서 회원의 정보를 조회합니다.
     *
     * @param memberId 회원의 id
     * @return Member 객체
     */
    public Member findById(Long memberId) {

        Member member = memberRepository.findByIdAndIsDeletedFalse(memberId).orElseThrow(
                () -> new MemberNotFoundException(String.valueOf(memberId),
                        "해당 id를 가진 회원을 찾을 수 없습니다."));
        return member;
    }

    /**
     * 소셜 로그인 요청이 들어올 때, 회원이 존재하면 회원을 반환하고 없을 경우 회원을 새로 만들고 반환합니다.
     *
     * @param email      사용자의 이메일
     * @param nickname   사용자의 닉네임
     * @param roleType   회원을 새로 만들 경우, 회원의 역할
     * @param socialType 회원을 새로 만들 경우, 회원 테이블에 저장될 소셜 로그인 타입(ex. 구글, 카카오)
     * @return Member 객체
     */
    @Transactional
    public Member findOrCreateSocialMember(String email, String nickname, RoleType roleType,
            SocialType socialType) {

        Optional<Member> memberOptional =
                memberRepository.findByEmailAndSocialTypeAndIsDeletedFalse(email,
                        socialType);

        if (memberOptional.isPresent()) {
            return memberOptional.get();
        }

        Member member = Member.create(roleType, socialType, email, nickname);
        Member savedMember = memberRepository.save(member);
        log.info("회원 가입이 완료되었습니다. memberId = {}", savedMember.getId());

        return savedMember;
    }

    public Member getMember(Long memberId) {

        return memberRepository.findById(memberId)
                // TODO 에러코드 수정 예정
                .orElseThrow(
                        () -> new RuntimeException("회원이 존재하지 않습니다."));
    }

    /**
     * 사용자의 정보를 조회하고 컨트롤러로 반환하는 메서드입니다.
     *
     * @param memberId 사용자의 ID
     * @return 내 정보 조회 응답 DTO
     */
    public MyInfoGetResponseDto getMyInformation(Long memberId) {

        Member member = memberRepository.findByIdAndIsDeletedFalse(memberId).orElseThrow(
                () -> new MemberNotFoundException(String.valueOf(memberId),
                        "해당 id를 가진 회원을 찾을 수 없습니다.")
        );

        int postCount = postRepository.countByAuthorIdAndIsDeletedFalse(memberId);

        int commentCount = commentRepository.countByCommenterIdAndIsDeletedFalse(memberId);

        return MyInfoGetResponseDto.of(member, postCount, commentCount);
    }

    /**
     * 사용자의 정보를 수정하고 변경사항을 컨트롤러로 반환하는 메서드입니다.
     *
     * @param memberId 사용자의 ID
     * @param nickname 변경할 사용자의 닉네임
     * @return 내 정보 수정 응답 DTO
     */
    @Transactional
    public MyInfoEditResponseDto editMyInformation(Long memberId, String nickname) {

        Member member = getActiveMemberOrThrow(memberId);

        member.editProfile(nickname);

        return MyInfoEditResponseDto.of(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getProfileImageUrl()
        );
    }

    /**
     * 사용자가 작성한 전체 게시글을 페이지네이션 방식으로 조회합니다.
     *
     * @param memberId 사용자 ID
     * @param pageable 페이지네이션 정보
     * @return 사용자가 작성한 게시글 목록 응답 DTO
     */
    public MyPostListResponseDto getMyPosts(Long memberId, Pageable pageable) {

        validateMemberActiveExists(memberId);

        // 작성한 게시글을 Page 객체으로 조회
        Page<Post> pageByAuthorIdWithPageable = postRepository.findByAuthorIdAndIsDeletedFalseOrderByCreatedAtDesc(
                memberId, pageable);

        List<MyPostSummaryDto> postSummaries = convertPostsToSummaries(pageByAuthorIdWithPageable);

        PaginationDto paginationDto = getPaginationDto(pageByAuthorIdWithPageable);

        // Page 객체를 DTO로 변환
        return MyPostListResponseDto.of(
                postSummaries,
                paginationDto
        );
    }

    private List<MyPostSummaryDto> convertPostsToSummaries(Page<Post> pagePosts) {
        return pagePosts.getContent().stream()
                .map(post -> {
                    int commentCount = commentRepository.countByPostIdAndIsDeletedFalse(post.getId());
                    return MyPostSummaryDto.of(post, commentCount);
                })
                .toList();
    }

    private PaginationDto getPaginationDto(Page<?> page) {
        return new PaginationDto(
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    /**
     * 사용자가 작성한 전체 댓글을 페이지네이션 방식으로 조회합니다.
     *
     * @param memberId 사용자 ID
     * @param pageable 페이지네이션 정보
     * @return 사용자가 작성한 댓글 목록 응답 DTO
     */
    public MyCommentListResponseDto getMyComments(Long memberId, Pageable pageable) {

        validateMemberActiveExists(memberId);

        // 작성한 댓글을 Page 객체로 조회
        Page<Comment> pageByCommenterIdWithPageable =
                commentRepository.findByCommenterIdAndIsDeletedFalseOrderByCreatedAtDesc(
                memberId, pageable
        );

        List<MyCommentSummaryDto> commentSummaries = convertCommentsToSummaries(pageByCommenterIdWithPageable);

        PaginationDto paginationDto = getPaginationDto(pageByCommenterIdWithPageable);

        // Page 객체를 DTO로 변환
        return MyCommentListResponseDto.of(
                commentSummaries, paginationDto
        );
    }

    private List<MyCommentSummaryDto> convertCommentsToSummaries(Page<Comment> pageComments) {
        return pageComments.getContent().stream()
                .map(MyCommentSummaryDto::of)
                .toList();
    }

    /**
     * 주어진 memberId로 활성화된 회원을 조회합니다. 회원이 존재하지 않거나 삭제된 경우 MemberNotFoundException을 발생시킵니다.
     *
     * @param memberId 조회할 회원의 ID
     * @return 활성화된 회원 객체
     * @throws MemberNotFoundException 회원이 존재하지 않거나 삭제된 경우
     */
    public Member getActiveMemberOrThrow(Long memberId) {

        return memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new MemberNotFoundException(null,
                        String.format("활성화된 회원을 찾을 수 없습니다. memberId: %d", memberId)));
    }

    /**
     * 주어진 memberId가 존재하고, 활성 상태의 회원인지 확인합니다.
     *
     * @param memberId 확인할 회원의 ID
     * @throws MemberNotFoundException 해당 ID를 가진 회원이 존재하지 않거나 삭제된 경우 예외 발생
     */
    public void validateMemberActiveExists(Long memberId) {

        if (!memberRepository.existsByIdAndIsDeletedFalse(memberId)) {
            throw new MemberNotFoundException(null,
                    String.format(
                            "해당 id를 가진 회원을 찾을 수 없습니다. memberId: %d",
                            memberId
                    ));
        }
    }

    /**
     * 사용자가 보유한 배지 목록을 조회합니다.
     *
     * @param memberId 사용자 ID
     * @return 사용자가 보유한 배지 목록 응답 DTO
     */
    public MyBadgeListResponseDto getMyBadges(Long memberId) {

        List<MemberBadge> memberBadgeList = memberBadgeRepository.findByMemberId(memberId);

        return MyBadgeListResponseDto.of(memberBadgeList);
    }
}