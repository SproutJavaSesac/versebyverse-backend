package today.sesac.versebyverse.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import today.sesac.versebyverse.member.dto.response.MyCommentSummaryDto;
import today.sesac.versebyverse.member.dto.response.MyInfoEditResponseDto;
import today.sesac.versebyverse.member.dto.response.MyInfoGetResponseDto;
import today.sesac.versebyverse.member.dto.response.MyPostListResponseDto;
import today.sesac.versebyverse.member.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me/posts")
    public ApiResponse<MyPostListResponseDto> getMemberPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        Long memberId = userPrincipal.getMemberId();

        MyPostListResponseDto myPostListResponseDto = memberService.getMemberPosts(memberId,
                PageRequest.of(page, size));

        return ApiResponse.success(myPostListResponseDto);
    }

    /**
     * 사용자가 작성한 전체 댓글을 페이지네이션 방식으로 조회합니다.
     *
     * @param page 페이지 번호 (0부터 시작)
     * @param size 페이지 크기 (기본값: 10)
     * @param userPrincipal 사용자의 인증 정보
     * @return 사용자가 작성한 댓글 목록 응답 DTO
     */
    @GetMapping("/me/comments")
    public ApiResponse<MyCommentListResponseDto> getMyComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        Long memberId = userPrincipal.getMemberId();

        MyCommentListResponseDto myCommentListResponseDto = memberService.getMyComments(
                memberId, PageRequest.of(page, size));

        return ApiResponse.success(myCommentListResponseDto);
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