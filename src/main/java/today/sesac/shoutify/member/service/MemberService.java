package today.sesac.shoutify.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.member.dto.MyPostListResponseDto;
import today.sesac.shoutify.member.dto.MyPostSummary;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;
import today.sesac.shoutify.member.exception.MemberNotFoundException;
import today.sesac.shoutify.member.repository.MemberRepository;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.repository.PostRepository;

/**
 * TODO: 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 설명 추가
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

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

    //TODO: 프론트 테스트 - 수정할 것
    public MyPostListResponseDto getMemberPosts(Long memberId, int page, int size) {

        // TODO: 프론트 테스트할 때, PostRepository 수정하지 않기 위해 post 전부 불러옴. 수정 필수!!
        List<Post> memberPosts = postRepository.findAll().stream()
                .filter(post -> post.getAuthor().getId().equals(memberId))
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());

        int totalCount = memberPosts.size();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, totalCount);

        List<Post> pagedPosts = fromIndex < totalCount
                ? memberPosts.subList(fromIndex, toIndex)
                : new ArrayList<>();

        List<MyPostSummary> postSummaries = pagedPosts.stream()
                .map(this::convertToSummary)
                .collect(Collectors.toList());

        MyPostListResponseDto response = new MyPostListResponseDto();
        response.setPosts(postSummaries);
        response.setCurrentPage(page);
        response.setTotalPage(totalPages);
        response.setTotalCount(totalCount);
        response.setPageSize(size);
        response.setHasNext(page < totalPages);
        response.setHasPrev(page > 1);

        return response;
    }

    private MyPostSummary convertToSummary(Post post) {
        MyPostSummary summary = new MyPostSummary();

        summary.setPostId(post.getId());
        summary.setBeforeTitle(post.getBeforeTitle());
        summary.setAfterTitle(post.getAfterTitle());
        summary.setBeforeContent(post.getBeforeContent());
        summary.setAfterContent(post.getAfterContent());
        summary.setCreatedAt(post.getCreatedAt());
        summary.setEmotionType(post.getEmotionType());
        summary.setConceptType(post.getConceptType());
        summary.setReactionCount(15);    // TODO: 프론트 테스트 - 리액션 미구현된 관계로 리액션 수하드코딩
        summary.setCommentCount(13);    // TODO: 프론트 테스트 - 댓글 개수 하드코딩
        summary.setImageUrl(post.getImageUrl());
        summary.setHidden(post.isHidden());

        return summary;
    }
}
