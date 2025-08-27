package today.sesac.versebyverse.post.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import today.sesac.versebyverse.global.domain.Emotion;
import today.sesac.versebyverse.global.domain.Genre;
import today.sesac.versebyverse.member.entity.Member;
import today.sesac.versebyverse.member.entity.RoleType;
import today.sesac.versebyverse.member.entity.SocialType;
import today.sesac.versebyverse.post.entity.Post;

@DataJpaTest
@EnableJpaAuditing
@DisplayName("[DataJpaTest] PostRepository 테스트")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Member author;

    private Post savedPost;

    @BeforeEach
    void setUp() {

        // 실제 Member 엔티티를 데이터베이스에 저장
        author = Member.create(
                RoleType.ROLE_USER, SocialType.GOOGLE, "test@email.com", "testNickname"
        );
        author = entityManager.persistAndFlush(author);

        savedPost = postRepository.save(
                Post.createPost(
                        author, "1-beforeContent", "1-afterContent", "1-beforeTitle",
                        "1-afterTitle", null, Emotion.HAPPY, Genre.COLUMN
                )
        );

        System.out.println("Saved Post ID: " + savedPost.getId());
    }

    @Test
    @DisplayName("[정상] - 게시물 ID로 게시물을 조회한다.")
    void findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse() {

        // when
        Optional<Post> foundPost = postRepository
                .findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse(savedPost.getId());

        // then
        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getId()).isEqualTo(savedPost.getId());
        assertThat(foundPost.get().getAuthor().getId()).isEqualTo(author.getId());
        assertThat(foundPost.get().getBeforeContent()).isEqualTo("1-beforeContent");
        assertThat(foundPost.get().getAfterContent()).isEqualTo("1-afterContent");
        assertThat(foundPost.get().getBeforeTitle()).isEqualTo("1-beforeTitle");
        assertThat(foundPost.get().getAfterTitle()).isEqualTo("1-afterTitle");
        assertThat(foundPost.get().getImageUrl()).isNull();
        assertThat(foundPost.get().getEmotionType()).isEqualTo(Emotion.HAPPY);
        assertThat(foundPost.get().getGenreType()).isEqualTo(Genre.COLUMN);
    }

    @Test
    @DisplayName("[예외] - 삭제된 게시물 ID로 조회 시 조회되지 않는다.")
    void findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse_deleted() {
        // given
        savedPost.delete();

        // when
        Optional<Post> foundPost = postRepository
                .findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse(savedPost.getId());

        // then
        assertThat(foundPost).isEmpty();
    }

    @Test
    @DisplayName("[예외] - 숨겨진 게시물 ID로 조회 시 조회되지 않는다.")
    void findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse_hidden() {
        // given
        savedPost.hide();

        // when
        Optional<Post> foundPost = postRepository
                .findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse(savedPost.getId());

        // then
        assertThat(foundPost).isEmpty();
    }

    @Test
    @DisplayName("[예외] - 차단된 게시물 ID로 조회 시 조회되지 않는다.")
    void findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse_blocked() {
        // given
        savedPost.block();

        // when
        Optional<Post> foundPost = postRepository
                .findByIdAndIsDeletedFalseAndIsBlockedFalseAndIsHiddenFalse(savedPost.getId());

        // then
        assertThat(foundPost).isEmpty();
    }
}