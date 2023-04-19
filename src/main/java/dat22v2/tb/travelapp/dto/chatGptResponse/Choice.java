package dat22v2.tb.travelapp.dto.chatGptResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Choice {
    public Message message;
    public String finish_reason;
    public int index;
}
