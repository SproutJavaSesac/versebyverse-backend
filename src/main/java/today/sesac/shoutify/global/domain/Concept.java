package today.sesac.shoutify.global.domain;

import lombok.Getter;

/**
 * 게시물의 컨셉 enum입니다.
 */
@Getter
public enum Concept {
    CLASSICAL_POETRY("classical_poetry"),
    POETRY("poetry"),
    NOVEL("novel"),
    DRAMA("drama"),
    ESSAY("essay");

    private final String concept;

    Concept(String concept) {
        this.concept = concept;
    }
}
