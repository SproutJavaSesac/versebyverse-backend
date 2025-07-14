package today.sesac.shoutify.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.comment.dto.request.CommentCreateRequestDto;
import today.sesac.shoutify.comment.dto.response.CommentCreateResponseDto;
import today.sesac.shoutify.comment.dto.response.CommentListResponseDto;
import today.sesac.shoutify.comment.entity.Comment;
import today.sesac.shoutify.comment.exception.CommentErrorCode;
import today.sesac.shoutify.comment.exception.CommentException;
import today.sesac.shoutify.comment.repository.CommentRepository;
import today.sesac.shoutify.global.exception.PermissionRequiredException;
import today.sesac.shoutify.member.entity.Member;
import today.sesac.shoutify.member.service.MemberService;
import today.sesac.shoutify.post.entity.Post;
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
    public CommentCreateResponseDto writeComment(CommentCreateRequestDto commentCreateRequestDto,
            Long commenterId, Long postId) {

        // TODO 해당 메서드 구현 PR 머지되어야 가능
        Post activePost = postQueryService.getActivePostById(postId);

        Member member = memberService.getActiveMemberOrThrow(
                commenterId); // TODO ActiveMember 가져오도록 수정 필요

        String afterContent = "AI가 변경해 준 content"; // TODO: AI 처리 로직 추가 예정

        Comment comment = createRootOrReplyComment(commentCreateRequestDto, afterContent,
                activePost, member);
        Comment savedComment = commentRepository.save(comment);
        savedComment.updatePath(); // 댓글 경로 자동 생성
        return CommentCreateResponseDto.of(savedComment);

    }

    private Comment createRootOrReplyComment(
            CommentCreateRequestDto commentCreateRequestDto,
            String afterContent, Post activePost, Member member) {

        if (commentCreateRequestDto.parentId() == null) {
            return Comment.createRootLevelComment(
                    commentCreateRequestDto.content(),
                    afterContent,
                    activePost,
                    member
            );
        }

        Comment parentComment = commentRepository.findById(commentCreateRequestDto.parentId())
                .orElseThrow(() -> new CommentException(
                        CommentErrorCode.COMMENT_NOT_FOUND, "parentId"));

        return Comment.createReplyComment(
                commentCreateRequestDto.content(),
                afterContent,
                activePost,
                parentComment,
                member
        );
    }

    /**
     * 게시글 ID에 해당하는 댓글 목록을 페이지네이션 방식으로 조회합니다.
     *
     * @param postId   게시글 ID
     * @param pageable 페이지네이션 정보
     * @return 댓글 목록 응답 DTO
     */
    public CommentListResponseDto getCommentsByPostId(Long postId, Pageable pageable) {

        postQueryService.validateActivePostByIdOrThrow(postId);

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
    public void deleteComment(Long commentId, Long memberId) {

        validateIsSameCommenterAsMember(commentId, memberId);
        commentRepository.deleteById(commentId);
    }

    private void validateIsSameCommenterAsMember(Long commentId, Long memberId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND,
                        "commentId"));
        if (!comment.getCommenter().getId().equals(memberId)) {
            throw new PermissionRequiredException("commentId", String.format(
                    "댓글 작성자와 요청한 회원이 일치하지 않습니다. 요청한 회원 ID: %d, 댓글 작성자 ID: %d",
                    memberId, comment.getCommenter().getId()
            ));
        }
    }
}