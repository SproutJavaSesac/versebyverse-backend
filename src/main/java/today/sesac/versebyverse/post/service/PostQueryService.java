package today.sesac.versebyverse.post.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.sesac.versebyverse.comment.repository.CommentRepository;
import today.sesac.versebyverse.global.domain.Concept;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.post.dto.AuthorPostCountDto;
import today.sesac.versebyverse.post.dto.response.PostSingleQueryResponseDto;
import today.sesac.versebyverse.post.dto.response.PostSummaryResponseDto;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.exception.PostErrorCode;
import today.sesac.versebyverse.post.exception.PostException;
import today.sesac.versebyverse.post.repository.PostRepository;
import today.sesac.versebyverse.reaction.dto.response.ReactionResponseDto;
import today.sesac.versebyverse.reaction.repository.ReactionRepository;
import today.sesac.versebyverse.reaction.service.ReactionService;
import today.sesac.versebyverse.reaction.utils.TargetType;

/**
 * 게시글 조회 service.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final ReactionRepository reactionRepository;

    private final ReactionService reactionService;
    //TODO 일관성을 위해 service에 의존하게 코드 변경

    /**
     * 게시글 목록 리스트 조회.
     */
    public Page<PostSummaryResponseDto> getPosts(Concept conceptType, String sort,
            int page, int size) {

        //jpa 에게 요청하는 데이터 양식, 최신순 정렬이 기본
        Pageable pageable = PageRequest.of(page, size);

        //concept이 all일 경우 필터링은 false
        boolean filterByConcept = !Concept.ALL.equals(conceptType);

        //repository를 통해 jpa 가 db 에서 받아온 post 엔티티 + 페이지 정보
        Page<Post> postPage;

        if (!filterByConcept) {
            //filterByConcept이 false 일때 (concept이 all 이거나 null 이어서 필터링 없이 전체조회)
            postPage = switch (sort) {
                //전체조회 + comment순 정렬
                case "comments" -> postRepository.findAllOrderByCommentCount(pageable);
                //전체조회 + reaction순 정렬
                case "reactions" -> postRepository.findAllOrderByReactionCount(pageable);

                //전체 조회 + 최신순 정렬
                default -> postRepository.findAllOrderByCreatedAt(pageable);
            };
        } else { //concept별 필터링
            postPage = switch (sort) {
                //컨셉별 조회 + comment순 정렬
                case "comments" -> postRepository.findByConceptTypeOrderByCommentCount(conceptType,
                        pageable);
                //컨셉별 조회 + reaction순 정렬
                case "reactions" ->
                        postRepository.findByConceptTypeOrderByReactionCount(conceptType, pageable);
                //컨셉별 조회 + 최신순 정렬
                default -> postRepository.findByConceptType(conceptType, pageable);
            };
        }
        //Post 객체를 dto 객체로 변환
        return postPage.map(post -> {
            int commentCount =
                    commentRepository.countByPostIdAndIsDeletedFalseAndIsBlockedFalse(
                            post.getId());
            int reactionCount =
                    reactionRepository.countByPostId(
                            post.getId());

            return PostSummaryResponseDto.of(post, commentCount, reactionCount);
        });
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
     * 게시글 단건 상세 조회.
     *
     * @param postId   게시글 id
     * @param memberId 작성자 id
     * @return postSingleQueryResponseDto
     */
    public PostSingleQueryResponseDto getPostDetail(Long postId, Long memberId) {

        Post foundPost = postRepository.findById(postId).orElseThrow(
                () -> new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString()));

        //댓글 갯수 조회
        int commentCount =
                commentRepository.countByPostIdAndIsDeletedFalseAndIsBlockedFalse(
                        postId);

        ReactionResponseDto reactionInfo;
        if (memberId != null) {
            // 로그인 사용자: 개인 반응 정보 포함
            reactionInfo = reactionService.getReactions(TargetType.POST, postId, memberId);
        } else {
            // 비로그인 사용자: 전체 반응 통계만
            reactionInfo =
                    reactionService.getTotalReactionAndReactionDetailsByTargetType(null, postId,
                            TargetType.POST);
        }

        return PostSingleQueryResponseDto.of(foundPost, memberId, commentCount,
                reactionInfo.reactionTotalCount(), reactionInfo.myReaction(),
                reactionInfo.reactionDetails());
    }

    /**
     * 게시물 ID로 게시물을 조회합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물만 조회합니다.
     *
     * @param postId 게시물 ID
     * @return 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시물 Entity 객체
     */
    public Post getActivePostById(Long postId) {

        return postRepository
                .findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_FOUND, "postId"));
    }

    /**
     * 게시글이 존재하는지 확인합니다. 삭제되지 않고, 신고되지 않았으며, 숨겨지지 않은 게시글만 확인합니다.
     *
     * @param postId 게시글 ID
     * @throws PostException 게시글이 존재하지 않을 경우
     */
    public void validateActivePostByIdOrThrow(Long postId) {

        if (!postRepository.existsByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse(postId)) {
            throw new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString());
        }
    }

    /**
     * 게시글의 상태(정상/삭제/신고)와 관계없이 DB에 실제로 존재하는지 확인합니다.
     *
     * @param postId 게시글 ID
     * @throws PostException 게시글이 존재하지 않을 경우
     */
    public void validatePostExistsOrThrow(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new PostException(PostErrorCode.POST_NOT_FOUND, "postId");
        }
    }
}