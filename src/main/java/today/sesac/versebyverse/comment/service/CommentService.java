package today.sesac.versebyverse.comment.service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.ai.dto.request.CommentAiRequestDto;
import today.sesac.versebyverse.ai.prompt.PromptType;
import today.sesac.versebyverse.ai.service.CommentAiService;
import today.sesac.versebyverse.comment.dto.request.CommentCreateRequestDto;
import today.sesac.versebyverse.comment.dto.response.CommentCreateResponseDto;
import today.sesac.versebyverse.comment.dto.response.CommentListResponseDto;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.comment.exception.CommentErrorCode;
import today.sesac.versebyverse.comment.exception.CommentException;
import today.sesac.versebyverse.comment.repository.CommentRepository;
import today.sesac.versebyverse.global.exception.PermissionRequiredException;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.service.PostQueryService;
import today.sesac.versebyverse.reaction.dto.response.ReactionResponseDto;
import today.sesac.versebyverse.reaction.service.ReactionService;
import today.sesac.versebyverse.reaction.utils.TargetType;

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

    private final CommentAiService commentAiService;

    private final ReactionService reactionService;

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

        Post activePost = postQueryService.getActivePostById(postId);

        Member member = memberService.getActiveMemberOrThrow(
                commenterId);

        CommentAiRequestDto commentAiRequestDto =
                CommentAiRequestDto.of(activePost.getConceptType(),
                        commentCreateRequestDto.content(), null);
        String afterContent = commentAiService.executeAiWithValidation(commentAiRequestDto,
                PromptType.COMMENT_CONCEPT_TRANSFORM).getContent();

        Comment comment = createRootOrReplyComment(commentCreateRequestDto, afterContent,
                activePost, member);
        Comment savedComment = commentRepository.save(comment);
        savedComment.updatePath(); // 댓글 경로 자동 생성

        ReactionResponseDto reactionInfo =
                reactionService.addCountByReactionType(null, savedComment.getId(),
                        TargetType.COMMENT);

        return CommentCreateResponseDto.of(savedComment, reactionInfo.reactionTotalCount(),
                reactionInfo.reactionDetails());

    }

    /**
     * 댓글을 작성할 때, 루트 댓글 또는 답글을 생성합니다.
     *
     * @param commentCreateRequestDto 댓글 작성 요청 DTO
     * @param afterContent            AI가 변환한 후의 댓글 내용
     * @param activePost              작성할 게시글
     * @param member                  댓글 작성자 회원
     * @return 생성된 댓글 엔티티
     */
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

        Comment parentComment =
                commentRepository.findByIdAndIsDeletedFalse(commentCreateRequestDto.parentId())
                        .orElseThrow(() -> new CommentException(
                                CommentErrorCode.COMMENT_NOT_FOUND, "parentId"));

        // 신고된 경우, 신고된 댓글에서는 답글을 작성할 수 없습니다.
        if (parentComment.isBlocked()) {
            throw new CommentException(CommentErrorCode.INVALID_REPLY_REFERENCE, "parentId");
        }

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

        //post id에 대한 모든 댓글 id 추출
        List<Long> commentIds = pageByPostIdWithPageable.getContent().stream()
                .map(Comment::getId)
                .toList();

        //모든 댓글에 대한 리액션 정보 한번에 조회
        Map<Long, ReactionResponseDto> reactionsMap =
                reactionService.getReactionsForComments(commentIds);

        return new CommentListResponseDto(
                postId,
                pageByPostIdWithPageable,
                reactionsMap
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

    /**
     * isDeleted와 isBlocked의 필드가 false인 comment엔티티를 반환합니다.
     *
     * @param commentId 댓글 id
     * @return Comment  isDeleted=false && isBlocked=false인 comment 엔티티
     */
    public Comment getActiveCommentById(Long commentId) {

        return commentRepository.findByIdAndIsDeletedFalseAndIsBlockedFalse(commentId).orElseThrow(
                () -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND, "commentId")
        );
    }
}