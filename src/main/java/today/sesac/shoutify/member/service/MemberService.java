package today.sesac.shoutify.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.comment.entity.Comment;
import today.sesac.shoutify.comment.repository.CommentRepository;
import today.sesac.shoutify.global.response.PaginationDto;
import today.sesac.shoutify.member.dto.response.MyCommentListResponseDto;
import today.sesac.shoutify.member.dto.response.MyCommentSummary;
import today.sesac.shoutify.member.dto.response.MyInfoEditResponseDto;
import today.sesac.shoutify.member.dto.response.MyInfoGetResponseDto;
import today.sesac.shoutify.member.dto.response.MyPostListResponseDto;
import today.sesac.shoutify.member.dto.response.MyPostSummary;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;
import today.sesac.shoutify.member.exception.MemberNotFoundException;
import today.sesac.shoutify.member.repository.MemberRepository;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.repository.PostRepository;

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

    public MyInfoGetResponseDto getMemberInformation(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(String.valueOf(memberId),
                        "해당 id를 가진 회원을 찾을 수 없습니다.")
        );

        MyInfoGetResponseDto myInfoGetResponseDto = convertMemberToInfo(member);

        return myInfoGetResponseDto;
    }

    private MyInfoGetResponseDto convertMemberToInfo(Member member) {

        MyInfoGetResponseDto myInfoGetResponseDto = new MyInfoGetResponseDto();
        myInfoGetResponseDto.setMemberId(member.getId());
        myInfoGetResponseDto.setNickname(member.getNickname());
        myInfoGetResponseDto.setEmail(member.getEmail());
        myInfoGetResponseDto.setProfileImageUrl(null);

        List<Post> memberPosts = postRepository.findAll().stream()
                .filter(post -> post.getAuthor().getId().equals(member.getId()))
                .filter(post -> !post.isDeleted())
                .collect(Collectors.toList());
        List<Comment> memberComments = commentRepository.findAll().stream()
                .filter(comment -> comment.getCommenter().getId().equals(member.getId()))
                .filter(comment -> !comment.isDeleted())
                .collect(Collectors.toList());

        myInfoGetResponseDto.setPostCount(memberPosts.size());
        myInfoGetResponseDto.setReactionCount(
                (int) (Math.random() * 20) + 1); //TODO: 프론트 테스트 - 회원이 받은 총 리액션 개수 하드코딩
        myInfoGetResponseDto.setCommentCount(memberComments.size());

        return myInfoGetResponseDto;
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

    //TODO: 프론트 테스트 - 수정할 것
    public MyPostListResponseDto getMemberPosts(Long memberId, int page, int size) {

        // TODO: 프론트 테스트할 때, PostRepository 수정하지 않기 위해 post 전부 불러옴. 수정 필수!!
        List<Post> memberPosts = postRepository.findAll().stream()
                .filter(post -> post.getAuthor().getId().equals(memberId))
                .filter(post -> !post.isDeleted())
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());

        int totalCount = memberPosts.size();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalCount);

        List<Post> pagedPosts = fromIndex < totalCount
                ? memberPosts.subList(fromIndex, toIndex)
                : new ArrayList<>();

        List<MyPostSummary> postSummaries = pagedPosts.stream()
                .map(this::convertPostToSummary)
                .collect(Collectors.toList());

        MyPostListResponseDto response = new MyPostListResponseDto();
        PaginationDto paginationDto = new PaginationDto(page, totalPages, (long) totalCount, size,
                page + 1 < totalPages, page > 0);

        response.setPosts(postSummaries);
        response.setPagination(paginationDto);

        return response;
    }

    public MyCommentListResponseDto getMemberComments(Long memberId, int page, int size) {
        // TODO: 프론트 테스트할 때, CommentRepository 수정하지 않기 위해 comment 전부 불러옴. 수정 필수!!
        List<Comment> memberComments = commentRepository.findAll().stream()
                .filter(comment -> comment.getCommenter().getId().equals(memberId))
                .filter(comment -> !comment.isDeleted())
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());

        int totalCount = memberComments.size();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalCount);

        List<Comment> pagedComments = fromIndex < totalCount
                ? memberComments.subList(fromIndex, toIndex)
                : new ArrayList<>();

        List<MyCommentSummary> commentSummaries = pagedComments.stream()
                .map(this::convertCommentToSummary)
                .collect(Collectors.toList());

        MyCommentListResponseDto response = new MyCommentListResponseDto();
        PaginationDto paginationDto = new PaginationDto(page, totalPages, (long) totalCount, size,
                page + 1 < totalPages, page > 0);

        response.setComments(commentSummaries);
        response.setPagination(paginationDto);

        return response;
    }

    private MyPostSummary convertPostToSummary(Post post) {

        MyPostSummary summary = new MyPostSummary();

        summary.setPostId(post.getId());
        summary.setBeforeTitle(post.getBeforeTitle());
        summary.setAfterTitle(post.getAfterTitle());
        summary.setBeforeContent(post.getBeforeContent());
        summary.setAfterContent(post.getAfterContent());
        summary.setCreatedAt(post.getCreatedAt());
        summary.setEmotionType(post.getEmotionType());
        summary.setConceptType(post.getConceptType());
        summary.setReactionCount(
                (int) (Math.random() * 20) + 1);    // TODO: 프론트 테스트 - 리액션 미구현된 관계로 리액션 수 하드코딩
        summary.setCommentCount((int) (Math.random() * 20) + 1);    // TODO: 프론트 테스트 - 댓글 개수 하드코딩
        summary.setImageUrl(post.getImageUrl());
        summary.setIsHidden(post.isHidden());

        return summary;
    }

    private MyCommentSummary convertCommentToSummary(Comment comment) {

        MyCommentSummary summary = new MyCommentSummary();

        summary.setCommentId(comment.getId());
        summary.setPostId(comment.getPost().getId());
        summary.setPostTitle(comment.getPost().getAfterTitle());
        summary.setBeforeContent(comment.getBeforeContent());
        summary.setAfterContent(comment.getAfterContent());
        summary.setCreatedAt(comment.getCreatedAt());

        // TODO: 프론트 테스트 - 리액션 개수 하드코딩
        summary.setReactionCount((int) (Math.random() * 20) + 1);

        return summary;
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
}