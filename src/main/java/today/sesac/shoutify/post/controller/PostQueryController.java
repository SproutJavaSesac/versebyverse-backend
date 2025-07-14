package today.sesac.shoutify.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.global.response.PaginationDto;
import today.sesac.shoutify.post.dto.response.PageResponseDto;
import today.sesac.shoutify.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.shoutify.post.dto.response.PostSummaryResponseDto;
import today.sesac.shoutify.post.service.PostQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostQueryController {

    private final PostQueryService postQueryService;

    /**
     * 게시글 단건 상세 조회.
     */
    @GetMapping("/{postId}")
    public ApiResponse<PostSingleQueryResponseDto> getSinglePostDetail(
            @PathVariable("postId") Long postId) {
        Long memberId = 1L;
        PostSingleQueryResponseDto postSingleQueryResponseDto =
                postQueryService.getPostDetail(postId, memberId);
        return ApiResponse.success(postSingleQueryResponseDto);
    }

    /**
     * 게시글 목록 조회.
     */
    @GetMapping
    public ApiResponse<PageResponseDto<PostSummaryResponseDto>> getPostsList(
            @RequestParam(defaultValue = "ALL") Concept concept,
            // concept이 null 이면 all 인 경우와 같은 로직 사용(필터링 없이 최신순 조회)
            @RequestParam(defaultValue = "latest") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PostSummaryResponseDto> result = postQueryService.getPosts(concept, sort, page, size);
        PaginationDto paginationDto = new PaginationDto(
                result.getNumber(),
                result.getTotalPages(),
                result.getTotalElements(),
                result.getSize(),
                result.hasNext(),
                result.hasPrevious()
        );

        PageResponseDto<PostSummaryResponseDto> pageResponseDto =
                new PageResponseDto<>(result.getContent(), paginationDto);

        return ApiResponse.success(pageResponseDto);
    }

}
