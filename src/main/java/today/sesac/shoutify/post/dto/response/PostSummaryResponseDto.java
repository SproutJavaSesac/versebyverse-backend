package today.sesac.shoutify.post.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.shoutify.global.domain.Concept;
import today.sesac.shoutify.post.entity.Post;

/**
 * 게시글 목록 조회 시 하나의 게시글을
 * 나타내기 위한 dto.
 */
//image 값이 null인 경우도 있어서 image는 final 제외 -> 빌더쓰면 안 붙여도 됨
@Getter
@AllArgsConstructor(staticName = "of")
public class PostSummaryResponseDto {

    private final Long postId;

    private final String nickname;

    private final String afterTitle;

    private final String afterContent;

    private final LocalDateTime createdAt;

    private final int reactionCount;

    private final Long commentCount;

    private final Concept conceptType;

    private String imageUrl;

    /**
     * Service 에서 Post 객체를 Dto 객체로 바꾸기 위한 정적 메소드
     * Concept enum 타입을 문자열로 변환.
     *
     * @param post          Post 객체
     * @param reactionCount 반응 갯수(현재 0으로 고정)
     * @param commentCount  댓글 갯수
     * @return Page 객체 내부에 들어가는 Dto 객체
     */
    public static PostSummaryResponseDto of(Post post, Long commentCount, int reactionCount) {
        return new PostSummaryResponseDto(post.getId(), post.getAuthor().getNickname(),
                post.getAfterTitle(), post.getAfterContent(), post.getCreatedAt(),
                reactionCount, commentCount, post.getConceptType(), post.getImageUrl());
    }
}
