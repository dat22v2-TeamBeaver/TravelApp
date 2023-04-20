package dat22v2.tb.travelapp.service;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest.ChatGPTRequest;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest.Message;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptResponse.ChatGPTResponse;
import dat22v2.tb.travelapp.dto.openroute.DirectionResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.exceptions.TravelException;
import dat22v2.tb.travelapp.repository.TravelRepository;
import dat22v2.tb.travelapp.utility.APIKeyHolder;
import dat22v2.tb.travelapp.utility.MonoApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class TravelService {

    TravelRepository travelRepository;

    @Autowired
    APIKeyHolder apiKeyHolder;


    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    Message user = new Message("user","The trip takes 4 hours, the distance is around 300 km, and the weather is sunny except for a stopping point at McDonalds, where it is rainy, this trip is between Copenhagen and Odense.");
    ChatGPTRequest gptRequest = new ChatGPTRequest();


    public TravelResponse getTravel(TravelRequest body) throws TravelException {

        //TODO
        //the big boi
        //step1 body should contain departure and destination.
            //step 1.1 check database for this route and return it if information is less than 30? minutes old
            //step 1.2 Get geolocation for these
        //step2 get route for this geolocation
        //step3 find weather for the regions this route entails
        //step4 formulate the route and weather information
        //step5 ask chatGPT with information from step4
        //step6 formulate a response we can send back to user



        return null;
    }

    public GeolocationResponse getGeolocation(String location){

        String url = "https://api.openrouteservice.org/geocode/search?api_key=%s&text=%s";

        Mono<GeolocationResponse> test = MonoApiCaller.callGetApi(GeolocationResponse.class,url, apiKeyHolder.getOpenRouteAPIKey(),location);

        GeolocationResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }

    public DirectionResponse getTravelDetails(String start, String end){

        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=%s&start=%s&end=%s";

        Mono<DirectionResponse> test = MonoApiCaller.callGetApi(DirectionResponse.class,url,apiKeyHolder.getOpenRouteAPIKey(),start,end);

        DirectionResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }

    public ChatGPTResponse getChatGPTResponse(){

        gptRequest.addMessage(user);

        String url = "https://api.openai.com/v1/chat/completions";

        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization","Bearer " + apiKeyHolder.getChatGPTAPIKey());
        headers.put("Content-Type","application/json");

        Mono<ChatGPTResponse> test = MonoApiCaller.callTheNewPostApi(ChatGPTResponse.class,url,gptRequest,headers);

        ChatGPTResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }

}
