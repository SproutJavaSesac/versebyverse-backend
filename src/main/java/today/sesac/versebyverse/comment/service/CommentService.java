package today.sesac.versebyverse.comment.service;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.comment.dto.request.CommentCreateRequestDto;
import today.sesac.versebyverse.comment.dto.response.CommentCreateResponseDto;
import today.sesac.versebyverse.comment.dto.response.CommentListResponseDto;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.comment.repository.CommentRepository;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.service.PostQueryService;

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
     * TODO 생성 로직 수정, 검증 로직 추가
     *
     * @param commentCreateRequestDto 댓글 작성 요청 DTO
     * @param commenterId             댓글 작성자 회원 ID
     * @param postId                  작성할 게시글 ID
     * @return 댓글 작성 응답 DTO
     */
    @Transactional
    public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto,
            Long commenterId, Long postId) {

        return CommentCreateResponseDto.testOf(
                1L,
                postId,
                commenterId,
                "commenterTempNickname",
                commentCreateRequestDto.parentId(),
                commentCreateRequestDto.content(),
                0,
                0,
                Map.of(),
                false,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    /**
     * 게시글 ID에 해당하는 댓글 목록을 페이지네이션 방식으로 조회합니다.
     * TODO 검증 로직
     *
     * @param postId   게시글 ID
     * @param pageable 페이지네이션 정보
     * @return 댓글 목록 응답 DTO
     */
    public CommentListResponseDto getCommentsByPostId(Long postId, Pageable pageable) {

        Page<Comment> pageByPostIdWithPageable = commentRepository
                .findByPostIdOrderByPathAsc(postId, pageable);

        return new CommentListResponseDto(
                postId,
                pageByPostIdWithPageable
        );
    }

    /**
     * 댓글을 삭제합니다.
     * TODO 이미 삭제된 댓글에 예외를 보낼지, 삭제 성공 알림만 줄지 고려
     *
     * @param commentId 삭제할 댓글 ID
     */
    @Transactional
    public void deleteComment(Long commentId) {

        commentRepository.deleteById(commentId);
    }
}