package today.sesac.versebyverse.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.ai.dto.request.PostAiRequestDto;
import today.sesac.versebyverse.ai.dto.response.PostAiResponseDto;
import today.sesac.versebyverse.ai.service.PostAiService;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.service.MemberService;
import today.sesac.versebyverse.post.dto.request.PostProofreadCreateRequestDto;
import today.sesac.versebyverse.post.dto.request.PostProofreadPublishRequestDto;
import today.sesac.versebyverse.post.dto.response.PostCreateResponseDto;
import today.sesac.versebyverse.post.dto.response.PostProofreadCreateResponseDto;
import today.sesac.versebyverse.post.entity.Post;
import today.sesac.versebyverse.post.entity.PostProofreadAttempt;
import today.sesac.versebyverse.post.entity.PostProofreadTask;
import today.sesac.versebyverse.post.entity.TaskStatus;
import today.sesac.versebyverse.post.exception.PostProofReadErrorCode;
import today.sesac.versebyverse.post.exception.PostProofReadException;
import today.sesac.versebyverse.post.repository.PostProofreadAttemptRepository;
import today.sesac.versebyverse.post.repository.PostProofreadTaskRepository;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * 게시글 첨삭 관련 서비스입니다. 이 서비스는 AI로 첨삭된 게시글 첨삭본을 생성하고, 첨삭된 글을 게시글로 발행하는 기능을 제공합니다.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PostProofreadService {

    private final MemberService memberService;

    private final PostAiService postAiService;

    private final PostRepository postRepository;

    private final PostProofreadAttemptRepository postProofreadAttemptRepository;

    private final PostProofreadTaskRepository postProofreadTaskRepository;

    private static void validatePostProofreadStatus(PostProofreadTask task,
            PostProofreadAttempt attempt) {

        // - 이 Task가 이미 'COMPLETED' 상태인지 확인하여 중복 발행을 막습니다.
        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new PostProofReadException(
                    PostProofReadErrorCode.POST_PROOFREAD_ALREADY_PUBLISHED, "taskUuid");
        }

        // - 조회한 Attempt가 조회한 Task에 속해 있는지 확인하여 데이터가 일치하는지 검증합니다.
        if (!attempt.getTask().getId().equals(task.getId())) {
            throw new PostProofReadException(PostProofReadErrorCode.POST_PROOFREAD_TASK_MISMATCH,
                    "chosenAttemptId");
        }
    }

    /**
     * 게시글을 첨삭합니다.
     *
     * @param postProofreadCreateRequestDto 게시글 첨삭 생성 요청 DTO
     * @param memberId                      인증된 사용자의 ID
     * @return 첨삭된 게시글 정보
     */
    public PostProofreadCreateResponseDto createProofread(
            PostProofreadCreateRequestDto postProofreadCreateRequestDto, Long memberId) {

        // 시나리오에 따른 Task 생성 또는 조회
        PostProofreadTask currentTask =
                getCurrentPostProofreadTask(postProofreadCreateRequestDto, memberId);

        // AI 변환 첨삭
        PostAiResponseDto postAiResponseDto = postAiService.executeAiWithValidation(
                PostAiRequestDto.of(
                        postProofreadCreateRequestDto.title(),
                        postProofreadCreateRequestDto.genreType(),
                        postProofreadCreateRequestDto.emotionType(),
                        postProofreadCreateRequestDto.content()
                )
        );

        // AI 변환 첨삭 결과물로 새로운 Attempt(결과물)를 생성.
        PostProofreadAttempt newAttempt = PostProofreadAttempt.createProofreadAttempt(
                currentTask,
                postAiResponseDto.getEmotionType(),
                postAiResponseDto.getTitle(),
                postAiResponseDto.getContent()
        );
        PostProofreadAttempt savedAttempt = postProofreadAttemptRepository.save(newAttempt);

        return new PostProofreadCreateResponseDto(
                savedAttempt.getTask().getUuid(),
                savedAttempt.getId(),
                savedAttempt.getTask().getMember().getId(),
                savedAttempt.getTask().getMember().getNickname(),
                savedAttempt.getTask().getInitialTitle(),
                savedAttempt.getTask().getInitialContent(),
                savedAttempt.getAfterTitle(),
                savedAttempt.getAfterContent(),
                savedAttempt.getTask().getGenreType(),
                savedAttempt.getEmotionType(),
                savedAttempt.getCreatedAt(),
                savedAttempt.getUpdatedAt()
        );
    }

    private PostProofreadTask getCurrentPostProofreadTask(
            PostProofreadCreateRequestDto postProofreadCreateRequestDto, Long memberId) {

        boolean isFirstProofreadAttempt = postProofreadCreateRequestDto.taskUuid() == null;

        if (isFirstProofreadAttempt) {
            // 첫 번째 첨삭 요청이면, task 생성
            Member member = memberService.findById(memberId);
            PostProofreadTask task = PostProofreadTask.createProofreadTask(
                    member,
                    postProofreadCreateRequestDto.genreType(), // 사용자가 선택한 컨셉
                    postProofreadCreateRequestDto.title(), // 사용자가 입력한 원본 제목 저장
                    postProofreadCreateRequestDto.content() // 사용자의 원본 글 저장
            );
            return postProofreadTaskRepository.save(task);

        }

        // 기존에 미리보기 요청한 적 있는 경우 기존 task 반환
        return getExistingPostProofreadTask(postProofreadCreateRequestDto.taskUuid(), memberId);

    }

    private PostProofreadTask getExistingPostProofreadTask(
            String taskUuid, Long memberId) {

        // taskUuid와 memberId 같이 조회 -> 다른 사람의 글을 발행하는 가능성 차단.
        return postProofreadTaskRepository
                .findByUuidAndMemberId(taskUuid, memberId)
                .orElseThrow(() -> new PostProofReadException(
                        PostProofReadErrorCode.POST_PROOFREAD_NOT_FOUND,
                        "taskUuid"
                ));
    }

    /**
     * 첨삭된 글을 게시글로 발행합니다.
     *
     * @param taskUuid                       게시글 첨삭 task의 UUID
     * @param postProofreadPublishRequestDto 게시글 첨삭 발행 요청 DTO
     * @param memberId                       인증된 사용자의 ID
     * @return 발행된 게시글 정보
     */
    public PostCreateResponseDto publishProofread(
            String taskUuid, PostProofreadPublishRequestDto postProofreadPublishRequestDto,
            Long memberId) {

        // task, attempt 조회
        PostProofreadTask task = getExistingPostProofreadTask(taskUuid, memberId);
        PostProofreadAttempt attempt = postProofreadAttemptRepository
                .findById(postProofreadPublishRequestDto.chosenAttemptId())
                .orElseThrow(() -> new PostProofReadException(
                        PostProofReadErrorCode.POST_PROOFREAD_NOT_FOUND,
                        "chosenAttemptId"
                ));

        // 요청 데이터 정합성 검증
        validatePostProofreadStatus(task, attempt);

        // 게시글 발행
        Post post = Post.createPost(
                task.getMember(),
                task.getInitialContent(),
                attempt.getAfterContent(),
                task.getInitialTitle(),
                attempt.getAfterTitle(),
                postProofreadPublishRequestDto.imageUrl(),
                attempt.getEmotionType(),
                task.getGenreType()
        );
        Post savedPost = postRepository.save(post);

        // 현재 첨삭 task 종료
        task.complete(attempt); // Task 엔티티 내부에 상태 변경 로직을 두는 것이 좋습니다. (아래 설명 참조)

        return PostCreateResponseDto.of(savedPost);
    }
}