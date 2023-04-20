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
            Message system = new Message("system","You are a tour guide for a user, you take in information about a trip, about its duration, how long is the trip, the weather and then some tourist attractions, you must name and describe the locations of them. Summarize the information and give recommendations to the tourist attractions and about the weather, do the attractions in short bullet points. Mention the trip duration and distance. The token limit is 400.");
            messages.add(system);
        }
        messages.add(newMessage);
    }
}



