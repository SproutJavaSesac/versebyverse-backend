package today.sesac.shoutify.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import today.sesac.shoutify.post.entity.Post;
import today.sesac.shoutify.post.exception.PostErrorCode;
import today.sesac.shoutify.post.exception.PostException;
import today.sesac.shoutify.post.repository.PostRepository;

@DisplayName("[Mockito] PostQueryService 테스트")
@ExtendWith(MockitoExtension.class)
class PostQueryServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostQueryService postQueryService;

    @Test
    @DisplayName("[정상] 게시물 ID로 게시물 조회.")
    void getActivePostById() {
        // Given
        Long postId = 1L;
        Post mockPost = mock(Post.class);
        when(postRepository
                .findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(postId))
                .thenReturn(Optional.of(mockPost));

        // When
        Post result = postQueryService.getActivePostById(postId);

        // Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(mockPost);
    }

    @Test
    @DisplayName("[예외] 예외 발생 시 예외 종류, 메시지 확인")
    void getActivePostByIdThrowsException() {
        // Given
        Long postId = 999L;
        when(postRepository
                .findByIdAndIsDeletedFalseAndIsReportedFalseAndIsHiddenFalse(postId))
                .thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(
                () -> postQueryService.getActivePostById(postId))
                .isInstanceOf(PostException.class)
                .hasFieldOrPropertyWithValue("param", "postId")
                .hasFieldOrPropertyWithValue("IErrorCode", PostErrorCode.POST_NOT_FOUND);
    }
}