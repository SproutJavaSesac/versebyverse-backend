package today.sesac.versebyverse.comment.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import today.sesac.versebyverse.auth.service.UserPrincipal;
import today.sesac.versebyverse.comment.dto.request.CommentCreateRequestDto;
import today.sesac.versebyverse.comment.dto.response.CommentCreateResponseDto;
import today.sesac.versebyverse.comment.dto.response.CommentListResponseDto;
import today.sesac.versebyverse.comment.entity.Comment;
import today.sesac.versebyverse.comment.service.CommentService;
import today.sesac.versebyverse.global.config.TestSecurityConfig;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.global.domain.Genre;
import today.sesac.versebyverse.global.response.PaginationDto;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.RoleType;
import today.sesac.versebyverse.member.entity.SocialType;
import today.sesac.versebyverse.post.entity.Post;

@Import(TestSecurityConfig.class) //TODO: 테스트 커스텀 유저 추가 애노테이션 구현 필요
@DisplayName("[WebMvcTest] CommentController 테스트")
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[정상] 댓글 작성 테스트")
    void writeComment() throws Exception {

        // given
        long commentId = 1L; // 댓글 ID
        long postId = 1L; // 게시글 ID
        long parentId = 1L; // 부모 댓글 ID
        long commenterId = 1L; // 댓글 작성자 ID
        String commenterNickname = "commenterTempNickname"; // 댓글 작성자 닉네임
        String content = "댓글 내용"; // 댓글 내용

        CommentCreateRequestDto commentCreateRequestDto = new CommentCreateRequestDto(
                content, parentId);

        CommentCreateResponseDto responseDto = CommentCreateResponseDto.testOf(
                commentId, // commentId
                postId, // postId
                commenterId, // commenterId
                commenterNickname, // commenterNickname
                parentId, // parentId
                content, // content
                0, // level
                0, // reactionTotalCount
                Map.of(), // reactions
                false, // isDeleted
                false, // isBlocked
                LocalDateTime.now(), // createdAt
                LocalDateTime.now() // updatedAt
        );

        // UserPrincipal을 실제 객체로 생성 (mock 대신)
        UserPrincipal userPrincipal = UserPrincipal.create(
                commenterId,
                RoleType.ROLE_USER,
                SocialType.GOOGLE,
                "test@test.com"
        );

        when(commentService.writeComment(commentCreateRequestDto, commenterId, postId))
                .thenReturn(responseDto);

        // when, then
        mockMvc.perform(post(String.format("/api/v1/posts/%d/comments", postId))
                        .with(authentication(new OAuth2AuthenticationToken(
                                userPrincipal,
                                userPrincipal.getAuthorities(),
                                "google")))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(commentCreateRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result.commentId").value(commentId))
                .andExpect(jsonPath("$.result.postId").value(postId))
                .andExpect(jsonPath("$.result.commenterId").value(commenterId))
                .andExpect(jsonPath("$.result.commenterNickname").value(commenterNickname))
                .andExpect(jsonPath("$.result.parentId").value(parentId))
                .andExpect(jsonPath("$.result.content").value(content))
                .andExpect(jsonPath("$.result.level").value(0))
                .andExpect(jsonPath("$.result.reactionTotalCount").value(0))
                .andExpect(jsonPath("$.result.reactions").isEmpty())
                .andExpect(jsonPath("$.result.isDeleted").value(false))
                .andExpect(jsonPath("$.result.isBlocked").value(false))
                .andExpect(jsonPath("$.result.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.result.updatedAt").isNotEmpty());
    }

    @Test
    @DisplayName("[정상] 댓글 전체 조회 테스트 - 댓글 없음")
    void getComments() throws Exception {

        long totalCount = 0; // 전체 댓글 수
        long postId = 1L; // 게시글 ID

        // pagination
        int totalPages = 0; // 전체 페이지 수
        int page = 0; // 페이지 번호
        int size = 10; // 페이지 크기
        boolean hasNext = false; // 다음 페이지 여부
        boolean hasPrevious = false; // 이전 페이지 여부

        // CommentListResponseDto를 직접 생성하지 말고 Service에서 반환할 완성된 객체 Mock
        CommentListResponseDto mockResponse = mock(CommentListResponseDto.class);
        when(mockResponse.postId()).thenReturn(postId);
        when(mockResponse.comments()).thenReturn(List.of());

        // PaginationDto Mock
        PaginationDto mockPagination = mock(PaginationDto.class);
        when(mockPagination.currentPage()).thenReturn(page);
        when(mockPagination.totalPages()).thenReturn(totalPages);
        when(mockPagination.totalCount()).thenReturn(totalCount);
        when(mockPagination.pageSize()).thenReturn(size);
        when(mockPagination.hasNext()).thenReturn(hasNext);
        when(mockPagination.hasPrevious()).thenReturn(hasPrevious);

        when(mockResponse.pagination()).thenReturn(mockPagination);

        when(commentService
                .getCommentsByPostId(postId, PageRequest.of(page, size))).thenReturn(mockResponse);

        // when, then
        mockMvc.perform(
                        get(String.format("/api/v1/posts/%d/comments?page=%d&size=%d", postId, page, size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result.postId").value(postId))
                .andExpect(
                        jsonPath("$.result.comments").isEmpty())
                .andExpect(jsonPath("$.result.pagination.currentPage").value(page))
                .andExpect(
                        jsonPath("$.result.pagination.totalPages").value(totalPages))
                .andExpect(jsonPath("$.result.pagination.totalCount").value(totalCount))
                .andExpect(jsonPath("$.result.pagination.pageSize").value(size))
                .andExpect(jsonPath("$.result.pagination.hasNext").value(hasNext))
                .andExpect(jsonPath("$.result.pagination.hasPrevious").value(hasPrevious));

    }

    @Test
    @DisplayName("[정상] 댓글 삭제 테스트")
    void deleteComment() throws Exception {

        long postId = 1L; // 게시글 ID
        long commentId = 1L; // 삭제할 댓글 ID

        long memberId = 1L; // 삭제 요청한 회원 ID
        // UserPrincipal을 실제 객체로 생성 (mock 대신)
        UserPrincipal userPrincipal = UserPrincipal.create(
                memberId,
                RoleType.ROLE_USER,
                SocialType.GOOGLE,
                "test@test.com"
        );

        // when, then
        mockMvc.perform(delete(String.format("/api/v1/posts/%d/comments/%d", postId, commentId))
                        .with(authentication(new OAuth2AuthenticationToken(
                                userPrincipal,
                                userPrincipal.getAuthorities(),
                                "google"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result").value("성공적으로 댓글을 삭제했습니다."));
    }

    private Comment createMockRootComment(long commentId, long postId, long postAuthorId,
            String commenterNickname, long commenterId, String afterContent) {

        Member postAuthor = spy(Member.create(
                RoleType.ROLE_USER,
                SocialType.GOOGLE,
                "postAuthor@test.com",
                "postAuthorNickname"
        ));
        when(postAuthor.getId()).thenReturn(postAuthorId);

        Member commenter = spy(Member.create(
                RoleType.ROLE_USER,
                SocialType.GOOGLE,
                "commenter@test.com",
                commenterNickname
        ));
        when(commenter.getId()).thenReturn(commenterId);

        Post post = spy(Post.createPost(
                postAuthor,
                "변환 전 내용",
                "변환 후 내용",
                "변환 전 게시글 제목",
                "변환 후 게시글 제목",
                null,
                Emotion.HAPPY,
                Genre.BOOK_REVIEW
        ));
        when(post.getId()).thenReturn(postId);

        Comment comment = spy(Comment.createRootLevelComment(
                "변환 전 내용",
                afterContent,
                post,
                commenter
        ));
        when(comment.getId()).thenReturn(commentId);
        when(comment.getPost()).thenReturn(post);
        when(comment.getCommenter()).thenReturn(commenter);
        when(comment.isDeleted()).thenReturn(false);
        when(comment.isBlocked()).thenReturn(false);
        when(comment.getCreatedAt()).thenReturn(LocalDateTime.now());
        when(comment.getUpdatedAt()).thenReturn(LocalDateTime.now());
        when(comment.getLevel()).thenReturn(0); // Root level comment

        return comment;
    }
}