package today.sesac.versebyverse.post.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.post.entity.Post;

/**
 * 게시글 단건 조회 응답 dto.
 */
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class PostSingleQueryResponseDto {

    Long postId;

    Long authorId;

    String afterTitle;

    String afterContent;

    String nickname;

    LocalDateTime createdAt;

    String imgUrl;

    String conceptType;

    Boolean isMine;

    Boolean isHidden;

    int commentCount;

    int reactionCount;

    Emotion myReaction;

    Emotion authorSelectEmotion; //작성자 혹은 ai가 글에 지정한 감정

    Map<Emotion, Integer> reactionDetailCount;

    /**
     * dto of 정적 메서드.
     *
     * @param post            post 객체
     * @param memberId        회원 id
     * @param commentCount    게시글의 댓글 총 갯수
     * @param reactionCount   게시글의 반응 총 갯수
     * @param myReaction      사용자의 반응
     * @param reactionDetails 게시글의 반응별 갯수
     */
    public static PostSingleQueryResponseDto of(Post post, Long memberId,
            int commentCount, int reactionCount, Emotion myReaction,
            Map<Emotion, Integer> reactionDetails) {

        return new PostSingleQueryResponseDto(
                post.getId(),
                post.getAuthor().getId(),
                post.getAfterTitle(),
                post.getAfterContent(),
                post.getAuthor().getNickname(),
                post.getCreatedAt(),
                post.getImageUrl(),
                post.getConceptType().toString(),
                post.isMine(memberId),
                post.isHidden(),
                commentCount,
                reactionCount,
                myReaction,
                post.getEmotionType(),
                reactionDetails
        );
    }
}