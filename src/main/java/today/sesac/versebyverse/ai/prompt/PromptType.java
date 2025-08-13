package today.sesac.versebyverse.ai.prompt;

/**
 * AI 프롬프트 기능을 구분하는 열거형입니다.
 */
public enum PromptType {
    /**
     * 욕설 순화.
     */
    PROFANITY_REFINE,
    /**
     * 감정 분석.
     */
    EMOTION_ANALYSIS,
    /**
     * 게시글 컨셉 변환.
     */
    POST_CONCEPT_TRANSFORM,

    /**
     * 댓글 컨셉 변환.
     */
    COMMENT_CONCEPT_TRANSFORM
}