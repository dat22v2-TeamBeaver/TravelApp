package dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest;

import lombok.*;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ChatGPTRequest {
    public String model = "gpt-3.5-turbo";
    public ArrayList<Message> messages;
    public int max_tokens = 400;


    public void addMessage(Message newMessage){
        if(messages == null){
            messages = new ArrayList<>();
            final String content = "You are a tour guide for a user, you take information in this order; start location, destination, trip duration(Convert into hours when needed), trip distance, weather status start location, weather status destination. You will mention all of this information in the language of the country given, and then make a guide on the closest tourist attractions that are along the way. Token limit is 200.";
            Message system = new Message("system", content);
            messages.add(system);
        }
        messages.add(newMessage);
    }
}



