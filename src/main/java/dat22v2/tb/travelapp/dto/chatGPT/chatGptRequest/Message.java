package dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class Message {
    public String role;
    public String content;
}
