package today.sesac.versebyverse.comment.dto.response;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.reaction.dto.response.ReactionResponseDto;

/**
 * 댓글 목록 응답 DTO.
 */
public record CommentListResponseDto(

        Long postId,

        List<CommentResponseDto> comments,

        PaginationDto pagination
) {

    /**
     * 페이지 객체를 사용하여 댓글 목록 응답 DTO를 생성합니다.
     *
     * @param postId       게시글 ID
     * @param pageComments 댓글 페이지 정보
     */
    public CommentListResponseDto(
            Long postId,
            Page<Comment> pageComments,
            Map<Long, ReactionResponseDto> reactionsMap
    ) {

        this(
                postId,
                IntStream.range(0, pageComments.getContent().size())
                        .mapToObj(i -> {
                            Comment comment = pageComments.getContent().get(i);
                            ReactionResponseDto reactionInfo = reactionsMap.getOrDefault(
                                    comment.getId(),
                                    ReactionResponseDto.of(null, 0, Map.of()) // 기본값
                            );

                            return CommentResponseDto.of(
                                    comment,
                                    i,
                                    reactionInfo.reactionTotalCount(),
                                    reactionInfo.reactionDetails()
                            );
                        })
                        .toList(),
                new PaginationDto(
                        pageComments.getNumber(),
                        pageComments.getTotalPages(),
                        pageComments.getTotalElements(),
                        pageComments.getSize(),
                        pageComments.hasNext(),
                        pageComments.hasPrevious()
                )
        );
    }
}