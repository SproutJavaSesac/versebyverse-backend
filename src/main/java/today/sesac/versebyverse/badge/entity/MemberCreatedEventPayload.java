package today.sesac.versebyverse.badge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class MemberCreatedEventPayload {

    private Long memberId;

}
