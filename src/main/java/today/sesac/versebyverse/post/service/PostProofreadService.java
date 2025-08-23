package today.sesac.versebyverse.post.service;

import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.sesac.versebyverse.ai.dto.request.PostAiRequestDto;
import today.sesac.versebyverse.ai.dto.response.PostAiResponseDto;
import today.sesac.versebyverse.ai.prompt.PromptType;
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
import today.sesac.versebyverse.post.exception.PostErrorCode;
import today.sesac.versebyverse.post.exception.PostException;
import today.sesac.versebyverse.post.repository.PostProofreadAttemptRepository;
import today.sesac.versebyverse.post.repository.PostProofreadTaskRepository;
import today.sesac.versebyverse.post.repository.PostRepository;

/**
 * 게시글 교정 관련 서비스입니다. 이 서비스는 게시글 교정을 생성하고, 교정된 글을 게시글로 발행하는 기능을 제공합니다.
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

    /**
     * 게시글을 교정합니다.
     *
     * @param postProofreadCreateRequestDto 게시글 교정 생성 요청 DTO
     * @param memberId                      인증된 사용자의 ID
     * @return 생성된 게시글 교정 정보
     */
    public PostProofreadCreateResponseDto createProofread(
            PostProofreadCreateRequestDto postProofreadCreateRequestDto, Long memberId) {

        // 게시글 교정 세션을 생성하고, AI를 통해 교정된 내용을 생성합니다.

        PostAiResponseDto postAiResponseDto = postAiService.executeAiWithValidation(
                PostAiRequestDto.of(
                        postProofreadCreateRequestDto.title(),
                        postProofreadCreateRequestDto.conceptType(),
                        postProofreadCreateRequestDto.emotionType(),
                        postProofreadCreateRequestDto.content()
                ),
                PromptType.POST_CONCEPT_TRANSFORM
        );

        Member member = memberService.findById(memberId);
        PostProofreadTask task;

        // 1. 시나리오 분기: DTO에 taskUuid가 있는가?
        if (postProofreadCreateRequestDto.taskUuid() == null) {
            // --- 시나리오 A: 첫 번째 첨삭 요청 ---

            // 2-A. 새로운 Task(바구니)를 생성합니다.
            //      - UUID는 서버에서 생성합니다.
            //      - status는 'IN_PROGRESS'가 맞습니다.
            //      - finalAttempt는 아직 없으므로 'null'이 맞습니다.
            String newUuid = UUID.randomUUID().toString();

            task = PostProofreadTask.createProofreadTask(
                    newUuid,
                    member,
                    postProofreadCreateRequestDto.conceptType(), // 사용자가 선택한 컨셉
                    postProofreadCreateRequestDto.title(), // 사용자가 입력한 원본 제목 저장
                    postProofreadCreateRequestDto.content() // 사용자의 원본 글 저장
            );
            postProofreadTaskRepository.save(task);

        } else {
            // --- 시나리오 B: 재시도 요청 ---

            // 2-B. 기존 Task(바구니)를 찾아옵니다. (보안을 위해 memberId와 함께 조회)
            task = postProofreadTaskRepository
                    .findByUuidAndMemberId(postProofreadCreateRequestDto.taskUuid(), memberId)
                    .orElseThrow(() -> new PostException(
                            PostErrorCode.POST_NOT_FOUND,
                            "POST_PROOFREAD_TASK_NOT_FOUND"
                    ));
        }

        // 3. 공통 로직: 새로운 Attempt(결과물)를 생성하고 바구니에 담습니다.
        PostProofreadAttempt newAttempt = PostProofreadAttempt.createProofreadAttempt(
                task, // 방금 만들거나 찾아온 Task에 연결
                postAiResponseDto.getEmotionType(),
                postAiResponseDto.getTitle(),
                postAiResponseDto.getContent()
        );
        postProofreadAttemptRepository.save(newAttempt);

        return new PostProofreadCreateResponseDto(
                task.getUuid(),
                newAttempt.getId(),
                member.getNickname(),
                postAiResponseDto.getTitle(),
                postAiResponseDto.getContent(),
                task.getConcept(),
                newAttempt.getCreatedAt(),
                newAttempt.getUpdatedAt()
        );
    }

    /**
     * 교정된 글을 게시글로 발행합니다.
     *
     * @param taskUuid                       게시글 교정 세션의 UUID
     * @param postProofreadPublishRequestDto 게시글 교정 발행 요청 DTO
     * @param memberId                       인증된 사용자의 ID
     * @return 발행된 게시글 정보
     */
    public PostCreateResponseDto publishProofread(
            String taskUuid, PostProofreadPublishRequestDto postProofreadPublishRequestDto,
            Long memberId) {

        // 1. [조회] 필요한 엔티티들을 DB에서 찾아옵니다.
        // - taskUuid와 memberId를 함께 조회하여, 다른 사람의 글을 발행하는 것을 원천 차단합니다.
        PostProofreadTask task = postProofreadTaskRepository
                .findByUuidAndMemberId(taskUuid, memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 첨삭 세션이 없거나 권한이 없습니다."));

        // - chosenAttemptId로 선택된 첨삭본을 조회합니다.
        PostProofreadAttempt attempt = postProofreadAttemptRepository
                .findById(postProofreadPublishRequestDto.chosenAttemptId())
                .orElseThrow(() -> new IllegalArgumentException("선택된 첨삭본을 찾을 수 없습니다."));

        // 2. [검증] 조회한 데이터들이 올바른 관계인지 확인합니다.
        // - 이 Task가 이미 'COMPLETED' 상태인지 확인하여 중복 발행을 막습니다.
        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalArgumentException("이미 발행이 완료된 작업입니다.");
        }

        // - 조회한 Attempt가 조회한 Task에 속해 있는지 확인하여 데이터가 일치하는지 검증합니다.
        if (!attempt.getTask().getId().equals(task.getId())) {
            throw new IllegalArgumentException("첨삭 세션과 결과물이 일치하지 않습니다.");
        }

        // 3. [생성] 'Post' 엔티티를 생성합니다. 클라이언트가 보낸 콘텐츠가 아닌,
        //    DB에서 직접 조회한 안전한 데이터를 사용합니다.
        Post post = Post.createPost(
                task.getMember(),              // 작성자 정보는 Task에서 가져옴
                task.getInitialContent(),      // 원본 내용은 Task에서 가져옴
                attempt.getAfterContent(),     // AI 변환 내용은 Attempt에서 가져옴
                task.getInitialTitle(),        // 원본 제목은 Task에서 가져옴 (엔티티 수정 필요, 아래 설명 참조)
                attempt.getAfterTitle(),       // AI 변환 제목은 Attempt에서 가져옴
                postProofreadPublishRequestDto.imageUrl(),         // 이미지는 DTO에서 가져옴
                attempt.getEmotion(), // 감정 타입은 Attempt에서 가져옴
                task.getConcept()          // 컨셉 정보는 Task에서 가져옴 (엔티티 수정 필요, 아래 설명 참조)
        );
        Post savedPost = postRepository.save(post);

        // 4. [업데이트] 'Task'의 상태를 'COMPLETED'로 변경하고, 어떤 Attempt가 최종 선택되었는지 기록합니다.
        task.complete(attempt); // Task 엔티티 내부에 상태 변경 로직을 두는 것이 좋습니다. (아래 설명 참조)
        // taskRepository.save(task); // Task가 영속성 컨텍스트에 있다면 @Transactional에 의해 자동 변경 감지(Dirty Checking)되어 save 호출은 생략 가능

        // 5. [응답] 생성된 Post의 정보를 DTO에 담아 반환합니다.
        return PostCreateResponseDto.of(savedPost);
    }
}