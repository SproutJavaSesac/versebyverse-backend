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
	 * @param email
	 * @param nickname
	 * @return
	 */
	public Member createMember(RoleType roleType, SocialType socialType, String email, String nickname) {
		Member member = Member.create(roleType, socialType, email, nickname);
		Member savedMember = memberRepository.save(member);
		log.info("createMember: roleType = {}, socialType = {}, email = {}, nickname = {}",
				roleType, socialType, email, nickname);
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
		log.info("findById: roleType = {}, socialType = {}, email = {}, nickname = {}",
				member.getRoleType(), member.getSocialType(), member.getEmail(), member.getNickname());
		return member;
	}

	public Member findByEmailAndSocialType(String email, SocialType socialType) {
		Member member = memberRepository.findByEmailAndSocialType(email, socialType).orElseThrow(
				() -> new MemberNotFoundException(String.valueOf(email) + ", " + String.valueOf(socialType),
						"해당 email을 가진 회원을 찾을 수 없습니다.")
		);
		log.info("findByEmailAndSocialType: roleType = {}, socialType = {}, email = {}, nickname = {}",
				member.getRoleType(), member.getSocialType(), member.getEmail(), member.getNickname());
		return member;
	}

}
