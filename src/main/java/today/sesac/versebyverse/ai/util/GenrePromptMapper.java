package today.sesac.versebyverse.ai.util;

import today.sesac.versebyverse.ai.prompt.PromptType;
import today.sesac.versebyverse.global.domain.Genre;

/**
 * 장르와 프롬프트 타입을 매핑하는 유틸리티 클래스입니다.
 */
public class GenrePromptMapper {

    /**
     * 장르에 따라 게시글용 프롬프트 타입을 반환합니다.
     *
     * @param genre 장르 타입
     * @return 해당하는 프롬프트 타입
     * @throws IllegalArgumentException 지원하지 않는 장르인 경우
     */
    public static PromptType getPostPromptType(Genre genre) {

        return switch (genre) {
            case HIPSTER_FEED -> PromptType.POST_HIPSTER_FEED;
            case COMMENTARY -> PromptType.POST_COMMENTARY;
            case MIDNIGHT_RADIO -> PromptType.POST_MIDNIGHT_RADIO;
            case COLUMN -> PromptType.POST_COLUMN;
            case CONTRIBUTION -> PromptType.POST_CONTRIBUTION;
            case BOOK_REVIEW -> PromptType.POST_BOOK_REVIEW;
            case MODERN_LITERATURE -> PromptType.POST_MODERN_LITERATURE;
            case CLASSICAL_LITERATURE -> PromptType.POST_CLASSICAL_LITERATURE;
            case ESSAY -> PromptType.POST_ESSAY;
            case ALL -> throw new IllegalArgumentException("ALL 장르는 프롬프트 타입을 가질 수 없습니다.");
        };
    }

    /**
     * 장르에 따라 댓글용 프롬프트 타입을 반환합니다.
     *
     * @param genre 장르 타입
     * @return 해당하는 프롬프트 타입
     * @throws IllegalArgumentException 지원하지 않는 장르인 경우
     */
    public static PromptType getCommentPromptType(Genre genre) {

        return switch (genre) {
            case HIPSTER_FEED -> PromptType.COMMENT_HIPSTER_FEED;
            case COMMENTARY -> PromptType.COMMENT_COMMENTARY;
            case MIDNIGHT_RADIO -> PromptType.COMMENT_MIDNIGHT_RADIO;
            case COLUMN -> PromptType.COMMENT_COLUMN;
            case CONTRIBUTION -> PromptType.COMMENT_CONTRIBUTION;
            case BOOK_REVIEW -> PromptType.COMMENT_BOOK_REVIEW;
            case MODERN_LITERATURE -> PromptType.COMMENT_MODERN_LITERATURE;
            case CLASSICAL_LITERATURE -> PromptType.COMMENT_CLASSICAL_LITERATURE;
            case ESSAY -> PromptType.COMMENT_ESSAY;
            case ALL -> throw new IllegalArgumentException("ALL 장르는 프롬프트 타입을 가질 수 없습니다.");
        };
    }
} 