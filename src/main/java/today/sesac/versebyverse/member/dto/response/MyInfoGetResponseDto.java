package today.sesac.versebyverse.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.sesac.versebyverse.member.entity.Member;

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