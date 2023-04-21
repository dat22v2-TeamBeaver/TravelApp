package dat22v2.tb.travelapp.service;

import dat22v2.tb.travelapp.dto.WeatherResponse;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest.ChatGPTRequest;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptResponse.ChatGPTResponse;
import dat22v2.tb.travelapp.dto.openroute.DirectionResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.utility.APIKeyHolder;
import dat22v2.tb.travelapp.utility.MonoApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoteApiService {

    @Autowired
    APIKeyHolder apiKeyHolder;


    public WeatherResponse atmosphereResponse(double latitude, double longtitude) {
        String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";
        return MonoApiCaller.callGetApi(WeatherResponse.class, OPEN_WEATHER_API_URL,latitude,longtitude, apiKeyHolder.getWeatherAPIKey()).block();
    }

    public GeolocationResponse getGeolocationResponse(String location){
        String OPEN_ROUTE_GEOCODE_URL = "https://api.openrouteservice.org/geocode/search?api_key=%s&text=%s";
        return MonoApiCaller.callGetApi(GeolocationResponse.class, OPEN_ROUTE_GEOCODE_URL,
            apiKeyHolder.getOpenRouteAPIKey(),location).block();
    }

    public DirectionResponse getDirectionResponse(String start, String end){
        String OPEN_ROUTE_DIRECTIONS_URL = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=%s&start=%s&end=%s";
        return MonoApiCaller.callGetApi(DirectionResponse.class,OPEN_ROUTE_DIRECTIONS_URL,
            apiKeyHolder.getOpenRouteAPIKey(), start, end).block();
    }

    public ChatGPTResponse getChatGPTResponse(ChatGPTRequest body){
        String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
        return MonoApiCaller.callTheNewPostApi(ChatGPTResponse.class, OPENAI_API_URL,
            body, headersWithAuthorization(apiKeyHolder.getChatGPTAPIKey())).block();
    }

    private Map<String, String> headersWithAuthorization(String bearerToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","Bearer " + bearerToken);
        return headers;
    }
}
