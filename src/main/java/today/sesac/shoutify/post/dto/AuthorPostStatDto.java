package today.sesac.shoutify.post.dto;

import today.sesac.shoutify.member.entity.Member;

/**
 * Service 간 작성자별 게시글 통계 전달용 DTO.
 */
public record AuthorPostStatDto(
        Member author,
        Long postCount
) {

}