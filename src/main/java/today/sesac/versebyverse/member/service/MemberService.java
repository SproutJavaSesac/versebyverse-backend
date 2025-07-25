package today.sesac.versebyverse.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.comment.repository.CommentRepository;
import today.sesac.versebyverse.member.dto.response.MyCommentListResponseDto;
import today.sesac.versebyverse.member.dto.response.MyCommentSummaryDto;
import today.sesac.versebyverse.member.dto.response.MyInfoEditResponseDto;
import today.sesac.versebyverse.member.dto.response.MyInfoGetResponseDto;
import today.sesac.versebyverse.member.dto.response.MyPostListResponseDto;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.RoleType;
import today.sesac.versebyverse.member.entity.SocialType;
import today.sesac.versebyverse.member.exception.MemberNotFoundException;
import today.sesac.versebyverse.member.repository.MemberRepository;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * TODO: 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 설명 추가.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    /**
     * TODO: 서비스와 나머지(ex.controller) 사이도 DTO로 통신하기? return값 엔티티 그대로 말고 다른 방식으로 결정하기. 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 설명 추가
     */
    public Member createMember(RoleType roleType, SocialType socialType, String email,
            String nickname) {

        Member member = Member.create(roleType, socialType, email, nickname);
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    /**
     * 회원의 id로 db에서 회원의 정보를 조회합니다.
     *
     * @param memberId 회원의 id
     * @return Member 객체
     */
    public Member findById(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(String.valueOf(memberId),
                        "해당 id를 가진 회원을 찾을 수 없습니다."));
        return member;
    }

    public Member findByEmailAndSocialType(String email, SocialType socialType) {

        Member member = memberRepository.findByEmailAndSocialType(email, socialType).orElseThrow(
                () -> new MemberNotFoundException(email + ", " + socialType,
                        "해당 email을 가진 회원을 찾을 수 없습니다.")
        );
        return member;
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
     * @param memberId 사용자의 id
     * @return MyInfoGetResponseDto 객체
     */
    public MyInfoGetResponseDto getMemberInformation(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(String.valueOf(memberId),
                        "해당 id를 가진 회원을 찾을 수 없습니다.")
        );

        return convertMemberToInfo(member);
    }

    /**
     * 회원 정보를 dto로 전환합니다.
     * @param member 회원 객체
     * @return MyInfoGetResponseDto 객체
     */
    private MyInfoGetResponseDto convertMemberToInfo(Member member) {

        List<Post> memberPosts = postRepository.findAll().stream()
                .filter(post -> post.getAuthor().getId().equals(member.getId()))
                .filter(post -> !post.isDeleted())
                .toList();
        List<Comment> memberComments = commentRepository.findAll().stream()
                .filter(comment -> comment.getCommenter().getId().equals(member.getId()))
                .filter(comment -> !comment.isDeleted())
                .toList();

        return MyInfoGetResponseDto.create(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getProfileImageUrl(),
                memberPosts.size(),
                31,
                memberComments.size()
        );
    }

    public MyInfoEditResponseDto editMemberInformation(Long memberId, String nickname) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(String.valueOf(memberId),
                        "해당 id를 가진 회원을 찾을 수 없습니다.")
        );
        member.editProfile(nickname);
        memberRepository.save(member);

        MyInfoEditResponseDto myInfoEditResponseDto = new MyInfoEditResponseDto();
        myInfoEditResponseDto.setMemberId(member.getId());
        myInfoEditResponseDto.setNickname(member.getNickname());
        myInfoEditResponseDto.setEmail(member.getEmail());
        myInfoEditResponseDto.setProfileImageUrl(null);

        return myInfoEditResponseDto;
    }

    /**
     * 사용자가 작성한 전체 게시글을 페이지네이션 방식으로 조회합니다.
     *
     * @param memberId 사용자 ID
     * @param pageable 페이지네이션 정보
     * @return 사용자가 작성한 게시글 목록 응답 DTO
     */
    public MyPostListResponseDto getMyPosts(Long memberId, Pageable pageable) {

        getActiveMemberOrThrow(memberId);

        Page<Post> pageByAuthorIdWithPageable = postRepository.findByAuthorIdAndIsDeletedFalseOrderByCreatedAtDesc(
                memberId, pageable);

            return MyPostListResponseDto.of(
                    pageByAuthorIdWithPageable
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
        getActiveMemberOrThrow(memberId);

        Page<Comment> pageByAuthorIdWithPageable = commentRepository.findByCommenterIdAndIsDeletedFalseOrderByCreatedAtDesc(
                memberId, pageable
        );

        return MyCommentListResponseDto.of(
                pageByAuthorIdWithPageable
        );
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

}