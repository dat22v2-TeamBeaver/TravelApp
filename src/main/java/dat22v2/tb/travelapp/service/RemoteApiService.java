package dat22v2.tb.travelapp.service;

import dat22v2.tb.travelapp.dto.WeatherResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.utility.APIKeyHolder;
import dat22v2.tb.travelapp.utility.MonoApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteApiService {

    @Autowired
    APIKeyHolder apiKeyHolder;


    public WeatherResponse atmosphereResponse(double latitude, double longtitude) {
        String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";
        return MonoApiCaller.callGetApi(WeatherResponse.class, OPEN_WEATHER_API_URL,latitude,longtitude, apiKeyHolder.getWeatherAPIKey()).block();
    }



    public GeolocationResponse getGeolocation(String location){
        String url = "https://api.openrouteservice.org/geocode/search?api_key=%s&text=%s";
        return MonoApiCaller.callGetApi(GeolocationResponse.class, url,
            apiKeyHolder.getOpenRouteAPIKey(),location).block();
    }





}
