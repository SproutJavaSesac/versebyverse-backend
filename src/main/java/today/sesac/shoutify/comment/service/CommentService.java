package today.sesac.shoutify.comment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.shoutify.comment.dto.request.CommentCreateRequestDto;
import today.sesac.shoutify.comment.dto.response.CommentCreateResponseDto;
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
                "commenterTmepNickname",
                commentCreateRequestDto.parentId(),
                commentCreateRequestDto.content(),
                0,
                List.of(),
                0,
                Map.of(),
                LocalDateTime.now()
        );
    }

}
