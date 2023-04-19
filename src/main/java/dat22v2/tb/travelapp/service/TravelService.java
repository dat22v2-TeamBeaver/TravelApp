package dat22v2.tb.travelapp.service;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
import dat22v2.tb.travelapp.dto.openroute.DirectionResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.entity.Travel;
import dat22v2.tb.travelapp.exceptions.TravelException;
import dat22v2.tb.travelapp.repository.TravelRepository;
import dat22v2.tb.travelapp.utility.MonoApiCaller;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class TravelService {

    TravelRepository travelRepository;

    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    String superSecretAPIKey = "5b3ce3597851110001cf62483266fbfe94d0473298e852c4f68ff7a9";

    public TravelResponse getTravel(TravelRequest body) throws TravelException { //Deprecated

        //check DB else ask API

        Mono<TravelResponse> test = MonoApiCaller.callGetApi(TravelResponse.class,"https://someUrl.com?number1=%s&number2=%s&number3=%s&",1,2,3);
        // Then return a response or exception depending on circumstances
        throw new TravelException("miav");

        //return null;
    }

    public GeolocationResponse getGeolocation(String location){

        String url = "https://api.openrouteservice.org/geocode/search?api_key=%s&text=%s";

        Mono<GeolocationResponse> test = MonoApiCaller.callGetApi(GeolocationResponse.class,url,superSecretAPIKey,location);

        GeolocationResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }

    public DirectionResponse getTravelDetails(String start, String end){

        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=%s&start=%s&end=%s";

        Mono<DirectionResponse> test = MonoApiCaller.callGetApi(DirectionResponse.class,url,superSecretAPIKey,start,end);

        DirectionResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }

}
