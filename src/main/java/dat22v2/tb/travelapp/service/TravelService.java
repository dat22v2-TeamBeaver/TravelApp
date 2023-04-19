package dat22v2.tb.travelapp.service;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
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




    public TravelResponse getTravel(TravelRequest body) throws TravelException {

        //check DB else ask API

        Mono<TravelResponse> test = MonoApiCaller.callGetApi(TravelResponse.class,"https://someUrl.com?number1=%s&number2=%s&number3=%s&",1,2,3);
        // Then return a response or exception depending on circumstances
        throw new TravelException("miav");

        //return null;
    }


}
