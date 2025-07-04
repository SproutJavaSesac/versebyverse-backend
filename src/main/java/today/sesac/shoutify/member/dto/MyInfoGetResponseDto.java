package today.sesac.shoutify.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyInfoGetResponseDto {

    private Long memberId;
    private String nickname;
    private String email;
    private String profileImageUrl;

    private int postCount;
    private int reactionCount;
    private int commentCount;
}
