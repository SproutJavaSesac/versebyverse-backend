package today.sesac.versebyverse.post.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.ai.dto.request.PostAiRequestDto;
import today.sesac.versebyverse.ai.dto.response.PostAiResponseDto;
import today.sesac.versebyverse.ai.service.PostAiService;
import today.sesac.versebyverse.badge.entity.BadgeOutbox;
import today.sesac.versebyverse.badge.entity.OutboxMessageType;
import today.sesac.versebyverse.badge.entity.OutboxStatus;
import today.sesac.versebyverse.badge.entity.PostCreatedEventPayload;
import today.sesac.versebyverse.badge.repository.BadgeOutboxRepository;
import today.sesac.versebyverse.global.exception.PermissionRequiredException;
import today.sesac.versebyverse.image.domain.ImagePurpose;
import today.sesac.versebyverse.image.exception.FileUploadException;
import today.sesac.versebyverse.image.service.ImageFileService;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.dto.request.PostCreateRequestDto;
import today.sesac.versebyverse.post.dto.response.PostCreateResponseDto;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.exception.PostErrorCode;
import today.sesac.versebyverse.post.exception.PostException;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * 게시글 생성, 삭제, 숨김 service.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostCommandService {

    private final PostRepository postRepository;
    // TODO PostService는  memberService만을 의존하고 member관련 오류는 memberService에선만 post관련은 PostService에서만 수행

    private final MemberService memberService;

    private final PostAiService postAiService;

    private final ApplicationEventPublisher eventPublisher;

    private final BadgeOutboxRepository badgeOutboxRepository;

    private final ObjectMapper objectMapper;

    private final ImageFileService imageFileService;

    /**
     * 게시물 작성 api.
     *
     * @param postCreateRequestDto 게시물 작성 dto
     * @param memberId             현재 로그인한 사용자의 id
     * @return PostCreateResponseDto 게시물 작성 dto
     */
    public PostCreateResponseDto savePost(PostCreateRequestDto postCreateRequestDto,
            Long memberId) {

        //1.작성자 정보 가져오기 (현재 사용자는 id=1로 하드코딩)
        Member author = memberService.getMember(memberId);
        //2. 사용자가 작성한 제목
        String beforeTitle = postCreateRequestDto.getTitle();
        //3.사용자가 작성한 원본내용 설정
        String beforeContent = postCreateRequestDto.getContent();

        // 이미지 파일 S3 업로드
        String imageUrl = null;
        if (postCreateRequestDto.getImageFile() != null && !postCreateRequestDto.getImageFile()
                .isEmpty()) {
            try {
                imageUrl = imageFileService.uploadImage(postCreateRequestDto.getImageFile(),
                        ImagePurpose.POST, memberId);
                log.info("이미지 업로드 완료: {}", imageUrl);
            } catch (Exception e) {
                log.error("이미지 업로드 실패", e);
                throw new FileUploadException("이미지 업로드에 실패했습니다.", e);
            }
        }

        //4.executeAi()﹒ai 요청dto of 생성자
        PostAiRequestDto postAiRequestDto =
                PostAiRequestDto.of(beforeTitle,
                        postCreateRequestDto.getGenreType(),
                        postCreateRequestDto.getEmotionType(), beforeContent);

        //5. ai 호출 게시글 변환
        PostAiResponseDto postAiResponseDto =
                postAiService.executeAiWithValidation(postAiRequestDto);

        //6. ai 처리된 afterTitle, afterContent 생성
        String afterTitle = postAiResponseDto.getTitle();
        String afterContent = postAiResponseDto.getContent();

        //정적 팩토리 메서드
        Post post = Post.createPost(
                author,
                beforeContent,
                afterContent,
                beforeTitle,
                afterTitle,
                imageUrl,
                postAiResponseDto.getEmotionType(),
                postAiResponseDto.getGenreType()
        );

        Post savedPost = postRepository.save(post);

//        eventPublisher.publishEvent(new PostCreatedEvent(author));

        try {

            PostCreatedEventPayload payload =
                    PostCreatedEventPayload.of(savedPost.getId(), savedPost.getAuthor().getId());

            String payloadJson = objectMapper.writeValueAsString(payload);

            BadgeOutbox badgeOutbox =
                    BadgeOutbox.create(OutboxMessageType.POST_CREATED, payloadJson,
                            OutboxStatus.WAITING);
            badgeOutboxRepository.save(badgeOutbox);
            log.info("BadgeOutbox created: {}", badgeOutbox);

        } catch (JsonProcessingException e) {
            log.error("Failed to serialize outbox payload", e);
            throw new RuntimeException("Failed to serialize outbox payload", e);
        }

        return PostCreateResponseDto.of(savedPost);
    }

    /**
     * 게시물 삭제.
     *
     * @param postId   삭제할 게시물의 id
     * @param memberId 현재 로그인한 사용자의 id
     */
    @Transactional
    public void deletePost(Long postId, Long memberId) {

        Post post = getPostById(postId);
        validatePostOwnership(post, memberId);
        //soft deleted
        post.delete();
        postRepository.save(post);
    }

    /**
     * 게시물 숨기기.
     *
     * @param postId   숨길 게시물의 id
     * @param memberId 현재 로그인한 사용자의 id
     */
    @Transactional
    public void hidePost(Long postId, Long memberId) {

        Post post = getPostById(postId);
        validatePostOwnership(post, memberId);
        post.hide();
        postRepository.save(post);
    }

    /**
     * 게시물 숨김 해제.
     *
     * @param postId   숨김 해제할
     * @param memberId 현재 로그인한 사용자의 id
     */
    @Transactional
    public void unhidePost(Long postId, Long memberId) {

        Post post = getPostById(postId);
        validatePostOwnership(post, memberId);
        post.unhide();
        postRepository.save(post);
    }

    /**
     * 작성자 권환 확인.
     */
    private void validatePostOwnership(Post post, Long memberId) {

        if (!post.getAuthor().getId().equals(memberId)) {
            throw new PermissionRequiredException(memberId.toString(), "권한이 없습니다.");
        }
    }

    /**
     * 게시글 존재 여부 확인.
     */
    private Post getPostById(Long postId) {

        return postRepository.findById(postId)
                .orElseThrow(
                        () -> new PostException(PostErrorCode.POST_NOT_FOUND, postId.toString()));
    }
}