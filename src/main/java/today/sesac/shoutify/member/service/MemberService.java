package today.sesac.shoutify.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;
import today.sesac.shoutify.member.exception.MemberNotFoundException;
import today.sesac.shoutify.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * TODO: 서비스와 나머지(ex.controller) 사이도 DTO로 통신하기?
	 */
	public void createMember(RoleType roleType, SocialType socialType, String nickname) {
		Member member = Member.create(roleType, socialType, nickname);
		memberRepository.save(member);
	}

	public Member findById(Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(
			() -> new MemberNotFoundException(String.valueOf(memberId), "해당 id를 가진 회원을 찾을 수 없습니다."));
		return member;
	}

}
