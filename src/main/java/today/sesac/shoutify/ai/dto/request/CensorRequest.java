package today.sesac.shoutify.ai.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CensorRequest {

    private String title;
    private String beforeText;


}
