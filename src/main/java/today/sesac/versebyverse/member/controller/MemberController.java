package today.sesac.versebyverse.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.member.dto.request.MyInfoEditRequestDto;
import today.sesac.versebyverse.member.dto.response.MyCommentListResponseDto;
import today.sesac.versebyverse.member.dto.response.MyInfoEditResponseDto;
import today.sesac.versebyverse.member.dto.response.MyInfoGetResponseDto;
import today.sesac.versebyverse.member.dto.response.MyPostListResponseDto;
import today.sesac.versebyverse.member.dto.response.MyRankingListResponseDto;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.ranking.entity.RankingCategory;
import today.sesac.versebyverse.ranking.entity.RankingPeriodType;
import today.sesac.versebyverse.ranking.service.RankingService;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    private final RankingService rankingService;

    /**
     * 탈퇴 요청을 수행합니다.
     *
     * @param userPrincipal 요청한 회원의 인증 정보.
     * @return 성공 메시지.
     */
    @DeleteMapping("/me")
    public ApiResponse<String> withdraw(@AuthenticationPrincipal UserPrincipal userPrincipal,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 1. 회원 탈퇴(소셜 로그인 연동 해제, db에서 회원 삭제)
        memberService.withdraw(userPrincipal.getMemberId(), userPrincipal.getName());

        // 2. 로그아웃(세션 무효화 및 쿠키 삭제)
        request.getSession().invalidate();

        Cookie cookie = new Cookie("JSESSIONID", "");   // TODO: https 적용할 때 변경하기. 쿠키 설정할 때 같이 변경
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ApiResponse.success("회원 탈퇴가 완료되었습니다.");
    }

    // TODO: 프론트 테스트를 위한 임시 마이페이지 게시글 확인 메서드 - 수정하기
    @GetMapping("/me/posts")
    public ApiResponse<?> getMemberPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,    //TODO: size 별도로 입력해야 할지 논의 필요
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        Long memberId = userPrincipal.getMemberId();

        MyPostListResponseDto myPostListResponseDto = memberService.getMemberPosts(memberId, page,
                size);

        return ApiResponse.success(myPostListResponseDto);
    }

    // TODO: 프론트 테스트를 위한 임시 마이페이지 댓글 확인 메서드 - 수정하기
    @GetMapping("/me/comments")
    public ApiResponse<?> getMemberComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,    //TODO: size 별도로 입력해야 할지 논의 필요
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        Long memberId = userPrincipal.getMemberId();

        MyCommentListResponseDto myCommentListResponseDto = memberService.getMemberComments(
                memberId, page, size);

        return ApiResponse.success(myCommentListResponseDto);
    }

    /**
     * 내 순위(랭킹) 정보를 조회합니다.
     *
     * @param category      조회할 순위(랭킹) 카테고리
     * @param periodType    조회할 기간 타입 (예: DAILY, WEEKLY 등)
     * @param maxCount      조회하는 랭킹 최대 개수(최대 30개)
     * @param userPrincipal 인증된 사용자 정보
     * @return 내 순위(랭킹) 정보
     */
    @GetMapping("/me/rankings")
    public ApiResponse<MyRankingListResponseDto> getMemberRankings(
            @RequestParam(defaultValue = "POST") RankingCategory category,
            @RequestParam(defaultValue = "DAILY") RankingPeriodType periodType,
            @RequestParam(defaultValue = "7") @Min(value = 1) @Max(value = 30) int maxCount,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        Long memberId = 1L;
//        Long memberId = userPrincipal.getMemberId();
        MyRankingListResponseDto myRankingListResponseDto = rankingService.getMyRankingByMemberId(
                memberId, category, periodType, maxCount);
        return ApiResponse.success(myRankingListResponseDto);
    }

    @GetMapping("/me")
    public ApiResponse<MyInfoGetResponseDto> getMemberInformation(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        Long memberId = userPrincipal.getMemberId();

        MyInfoGetResponseDto myInfoGetResponseDto = memberService.getMemberInformation(memberId);

        return ApiResponse.success(myInfoGetResponseDto);
    }

    @PutMapping("/me")
    public ApiResponse<MyInfoEditResponseDto> editMemberInformation(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody MyInfoEditRequestDto myInfoEditRequestDto
    ) {

        Long memberId = userPrincipal.getMemberId();

        MyInfoEditResponseDto myInfoEditResponseDto = memberService.editMemberInformation(memberId,
                myInfoEditRequestDto.getNickname());
        return ApiResponse.success(myInfoEditResponseDto);
    }
}