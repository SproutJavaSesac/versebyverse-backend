package today.sesac.shoutify.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.entity.RoleType;
import today.sesac.shoutify.member.entity.SocialType;
import today.sesac.shoutify.member.exception.MemberNotFoundException;
import today.sesac.shoutify.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * TODO: 서비스와 나머지(ex.controller) 사이도 DTO로 통신하기?
	 */
	public Member createMember(RoleType roleType, SocialType socialType, String nickname) {
		log.info("MemberService - createMember: roleType = {}, socialType = {}, nickname = {}", roleType, socialType,
			nickname);
		Member member = Member.create(roleType, socialType, nickname);
		Member savedMember = memberRepository.save(member);
		return savedMember;
	}

	public Member findById(Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(
			() -> new MemberNotFoundException(String.valueOf(memberId), "해당 id를 가진 회원을 찾을 수 없습니다."));
		return member;
	}

	// TODO: username? Email? 구분 어떻게 할지 정하기
	public Member findByUsername(String username) {
		Member member = memberRepository.findByUsername(username).orElseThrow(
			() -> new MemberNotFoundException(String.valueOf(username), "해당 username을 가진 회원을 찾을 수 없습니다.")
		);
		return member;
	}

}
