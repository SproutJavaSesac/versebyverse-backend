package today.sesac.versebyverse.post.dto;

import today.sesac.versebyverse.member.entity.Member;

/**
 * Service 간 작성자별 게시글 통계 전달용 DTO.
 */
public record AuthorPostStatDto(
        Member author,
        Long postCount
) {

}