package dat22v2.tb.travelapp.service;

import dat22v2.tb.travelapp.dto.WeatherResponse;
import dat22v2.tb.travelapp.utility.WeatherAPIKeyholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RemoteApiService {

    @Autowired
    WeatherAPIKeyholder weatherAPIKeyholder;


    public WeatherResponse atmosphereResponse(String location){
        WebClient webClient = WebClient.create();
        Mono<WeatherResponse> weatherMono = webClient.get()
                .uri("http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="
                        +weatherAPIKeyholder.getWeatherAPIKey())
                .retrieve()
                .bodyToMono(WeatherResponse.class);
        Mono<WeatherResponse> weatherResponse = WeatherResponse.fromMono(weatherMono);

        WeatherResponse response = weatherResponse.block();

        return response;

    }
}
