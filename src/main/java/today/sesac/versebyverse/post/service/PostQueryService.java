package today.sesac.versebyverse.post.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.dto.AuthorPostCountDto;
import today.sesac.versebyverse.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.exception.PostErrorCode;
import today.sesac.versebyverse.post.exception.PostException;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * 게시글 조회 service.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostRepository postRepository;

    /**
     * 게시글 단건 상세 조회.
     *
     * @param postId   게시글id
     * @param memberId 작성자id
     * @return postSingleQueryResponseDto
     */
    public PostSingleQueryResponseDto getPostDetail(Long postId, Long memberId) {

        Post foundPost = postRepository.findById(postId).orElseThrow(
                () -> new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString()));

        return PostSingleQueryResponseDto.of(foundPost.getId(), foundPost.getAfterTitle(),
                foundPost.getAfterContent(), foundPost.getAuthor().getNickname(),
                foundPost.getCreatedAt(), foundPost.getImageUrl(),
                foundPost.getConceptType().toString(), foundPost.isMine(memberId));
    }

    /**
     * 특정 기간 동안 활동 중인 작성자와 게시글 수를 조회합니다.
     *
     * @param startDate 시작 날짜
     * @param endDate   종료 날짜
     * @return 작성자와 게시글 수의 리스트
     */
    public List<AuthorPostCountDto> getAuthorAndPostCount(LocalDateTime startDate,
                                                          LocalDateTime endDate) {

        return postRepository.findActiveAuthorActivePostCount(startDate, endDate)
                .stream()
                .map(tuple -> new AuthorPostCountDto(
                        tuple.get(0, Member.class),
                        tuple.get(1, Long.class)
                ))
                .toList();
    }

    /**
     * 게시물 ID로 게시물을 조회합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물만 조회합니다.
     *
     * @param postId 게시물 ID
     * @return 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물 Entity 객체
     */
    public Post getActivePostById(Long postId) {

        return postRepository
                .findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_FOUND, "postId"));
    }
}