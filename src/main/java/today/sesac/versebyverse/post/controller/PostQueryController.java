package today.sesac.versebyverse.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.global.domain.Genre;
import today.sesac.versebyverse.global.response.ApiResponse;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.post.dto.response.PageResponseDto;
import today.sesac.versebyverse.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.versebyverse.post.dto.response.PostSummaryResponseDto;
import today.sesac.versebyverse.post.service.PostQueryService;

/**
 * 게시글 조회 관련 controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostQueryController {

    private final PostQueryService postQueryService;

    /**
     * 게시글 단건 조회.
     *
     * @param postId        게시글 id
     * @param userPrincipal 회원 id
     * @return 게시글 단건 응답 dto
     */
    @GetMapping("/{postId}")
    public ApiResponse<PostSingleQueryResponseDto> getSinglePostDetail(
            @PathVariable("postId") Long postId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long memberId = userPrincipal != null ? userPrincipal.getMemberId() : null;
        PostSingleQueryResponseDto postSingleQueryResponseDto =
                postQueryService.getPostDetail(postId, memberId);
        return ApiResponse.success(postSingleQueryResponseDto);
    }

    /**
     * 게시글 목록 조회.
     *
     * @param genre 글의 장르
     * @param sort  글 정렬
     * @param page  페이지
     * @param size  한 페이지에 보여지는 정보 크기
     * @return 공통 페이지 응답 dto
     */
    @GetMapping
    public ApiResponse<PageResponseDto<PostSummaryResponseDto>> getPostsList(
            @RequestParam(defaultValue = "ALL") Genre genre,
            // genre가 null 이면 all 인 경우와 같은 로직 사용(필터링 없이 최신순 조회)
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        //게시글 리스트 + 페이징 정보
        Page<PostSummaryResponseDto> result = postQueryService.getPosts(genre, sort, page, size);
        //페이지 정보만 따로 뽑아서 넣기
        PaginationDto paginationDto = new PaginationDto(
                result.getNumber(),
                result.getTotalPages(),
                result.getTotalElements(),
                result.getSize(),
                result.hasNext(),
                result.hasPrevious()
        );
        //게시글 리스트 + 위 paginationDto 묶기
        PageResponseDto<PostSummaryResponseDto> pageResponseDto =
                new PageResponseDto<>(result.getContent(), paginationDto);

        return ApiResponse.success(pageResponseDto);
    }

}