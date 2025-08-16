package today.sesac.versebyverse.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.member.entity.Member;

/**
 * 내 정보 조회 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class MyInfoGetResponseDto {

    private Long memberId;

    private String nickname;

    private String email;

    private String profileImageUrl;

    private int postCount;

    private int reactionCount;

    private int commentCount;

    /**
     * 내 정보 조회 응답 DTO를 생성합니다.
     *
     * @param member 사용자(요청하는 본인) 객체
     * @param postCount 사용자가 작성한 게시글 수
     * @param commentCount 사용자가 작성한 댓글 수
     * @return 내 정보 조회 응답 DTO
     */
    public static MyInfoGetResponseDto of(Member member, int postCount, int commentCount) {

        return new MyInfoGetResponseDto(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getProfileImageUrl(),
                postCount,
                31,  //TODO: 반응하기 미구현 관계로 하드코딩 - 수정하기
                commentCount
        );
    }
}