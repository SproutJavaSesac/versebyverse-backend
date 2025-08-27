package today.sesac.versebyverse.global.domain;

import lombok.Getter;

/**
 * 게시물의 장르 enum입니다.
 *
 * <p>각 장르는 게시물의 글쓰기 스타일과 톤앤매너를 정의합니다:</p>
 *
 * <ul>
 *   <li><strong>HIPSTER_FEED</strong> - 힙스터 문화와 트렌드를 반영한 현대적이고 독특한 글쓰기</li>
 *   <li><strong>COMMENTARY</strong> - 매거진 에디터의 시각으로 바라본 전문적이고 깊이 있는 분석</li>
 *   <li><strong>MIDNIGHT_RADIO</strong> - 새벽의 고요함과 사색을 담은 감성적인 에세이</li>
 *   <li><strong>COLUMN</strong> - 독자와 소통하는 친근하고 읽기 쉬운 칼럼 스타일</li>
 *   <li><strong>CONTRIBUTION</strong> - 비평적 시각으로 대상을 분석하고 평가하는 글</li>
 *   <li><strong>BOOK_REVIEW</strong> - 멘토의 경험과 지혜를 전달하는 가르침의 글</li>
 *   <li><strong>MODERN_LITERATURE</strong> - 현대 문학의 다양한 기법과 표현을 활용한 창작</li>
 *   <li><strong>CLASSICAL_LITERATURE</strong> - 고전 문학의 전통과 우아함을 담은 글쓰기</li>
 *   <li><strong>ESSAY</strong> - 현대 시의 자유로운 형식과 감각적인 표현</li>
 * </ul>
 *
 * <p>이 enum은 게시물 작성 시 사용자가 원하는 글쓰기 스타일을 선택할 수 있도록 합니다.</p>
 */
@Getter
public enum Genre {

    ALL,
    MODERN_LITERATURE, CLASSICAL_LITERATURE,
    COMMENTARY, COLUMN, CONTRIBUTION, BOOK_REVIEW,
    HIPSTER_FEED, MIDNIGHT_RADIO, ESSAY

}
