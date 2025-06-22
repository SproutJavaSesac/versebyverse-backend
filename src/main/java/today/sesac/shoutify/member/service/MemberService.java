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
	 * @param roleType
	 * @param socialType
	 * @param username
	 * @param nickname
	 * @return
	 */
	public Member createMember(RoleType roleType, SocialType socialType, String username, String nickname) {
		Member member = Member.create(roleType, socialType, username, nickname);
		Member savedMember = memberRepository.save(member);
		log.info("createMember: roleType = {}, socialType = {}, username = {}, nickname = {}",
			roleType, socialType, username, nickname);
		return savedMember;
	}

	/**
	 * 회원의 id로 db에서 회원의 정보를 조회합니다.
	 * @param memberId 회원의 id
	 * @return Member 객체
	 */
	public Member findById(Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(
			() -> new MemberNotFoundException(String.valueOf(memberId), "해당 id를 가진 회원을 찾을 수 없습니다."));
		log.info("findById: roleType = {}, socialType = {}, username = {}, nickname = {}",
			member.getRoleType(), member.getSocialType(), member.getUsername(), member.getNickname());
		return member;
	}

	public Member findByUsername(String username) {
		Member member = memberRepository.findByUsername(username).orElseThrow(
			() -> new MemberNotFoundException(String.valueOf(username), "해당 username을 가진 회원을 찾을 수 없습니다.")
		);
		log.info("findByUsername: roleType = {}, socialType = {}, username = {}, nickname = {}",
			member.getRoleType(), member.getSocialType(), member.getUsername(), member.getNickname());
		return member;
	}

}
