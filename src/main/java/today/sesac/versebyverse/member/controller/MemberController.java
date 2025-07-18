package today.sesac.versebyverse.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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