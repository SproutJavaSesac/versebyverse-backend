package today.sesac.shoutify.comment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.comment.dto.request.CommentCreateRequestDto;
import today.sesac.shoutify.comment.dto.response.CommentCreateResponseDto;
import today.sesac.shoutify.comment.dto.response.CommentListResponseDto;
import today.sesac.shoutify.comment.entity.Comment;
import today.sesac.shoutify.comment.repository.CommentRepository;
import today.sesac.shoutify.member.service.MemberService;
import today.sesac.shoutify.post.service.PostQueryService;

/**
 * CommentService는 댓글 관련 비즈니스 로직을 처리하는 서비스입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final MemberService memberService;

    private final PostQueryService postQueryService;

    /**
     * 댓글을 작성합니다.
     *
     * @param commentCreateRequestDto 댓글 작성 요청 DTO
     * @param commenterId             댓글 작성자 회원 ID
     * @param postId                  작성할 게시글 ID
     * @return 댓글 작성 응답 DTO
     */
    @Transactional
    public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto,
            Long commenterId, Long postId) {

        return CommentCreateResponseDto.of(
                1L,
                postId,
                commenterId,
                "commenterTempNickname",
                commentCreateRequestDto.parentId(),
                commentCreateRequestDto.content(),
                0,
                List.of(),
                0,
                Map.of(),
                LocalDateTime.now()
        );
    }

    /**
     * 게시글 ID에 해당하는 댓글 목록을 페이지네이션 방식으로 조회합니다.
     *
     * @param postId   게시글 ID
     * @param pageable 페이지네이션 정보
     * @return 댓글 목록 응답 DTO
     * @todo 검증 로직, 페이지 중간에 계층이 걸리는 경우, 삭제/신고인 경우 내용 변경
     */
    public CommentListResponseDto getCommentsByPostId(Long postId, Pageable pageable) {

        Page<Comment> pageByPostIdWithPageable = commentRepository.findPageByPostIdWithPageable(
                postId, pageable);

        int totalCommentCount = commentRepository.countByPostId(postId);

        return new CommentListResponseDto(
                totalCommentCount,
                postId,
                pageByPostIdWithPageable
        );
    }
}