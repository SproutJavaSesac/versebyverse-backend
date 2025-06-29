package today.sesac.shoutify.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;
import today.sesac.shoutify.member.exception.MemberNotFoundException;
import today.sesac.shoutify.member.repository.MemberRepository;

/**
 * TODO: 다음 pr(소셜로그인 예외, 테스트코드 추가)에서 설명 추가
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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
}
