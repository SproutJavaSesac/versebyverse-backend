package today.sesac.shoutify.post.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import today.sesac.shoutify.global.response.ApiResponse;
import today.sesac.shoutify.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.shoutify.post.service.PostQueryService;

/**
 * 게시글 조회 관련 controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/posts")
public class PostQueryController {
    private final PostQueryService postQueryService;

    //TODO 현재 사용자 memberId 1로 하드코딩 추후 변경 예정
    private final Long memberId = 1L;

    /**
     * 게시글 단건 상세 조회.
     */
    @GetMapping("/{postId}")
    public ApiResponse<PostSingleQueryResponseDto> findById(@PathVariable("postId") Long postId) {
        PostSingleQueryResponseDto postSingleQueryResponseDto =
                postQueryService.findById(postId, memberId);
        return ApiResponse.success(postSingleQueryResponseDto);
    }
}
