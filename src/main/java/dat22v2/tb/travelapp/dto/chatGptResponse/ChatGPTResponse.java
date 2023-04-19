package dat22v2.tb.travelapp.dto.chatGptResponse;

import dat22v2.tb.travelapp.dto.chatGptResponse.Choice;
import dat22v2.tb.travelapp.dto.chatGptResponse.Usage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatGPTResponse {
    public String id;
    public String object;
    public int created;
    public String model;
    public Usage usage;
    public ArrayList<Choice> choices;
}
