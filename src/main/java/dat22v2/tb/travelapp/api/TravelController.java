package dat22v2.tb.travelapp.api;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
import dat22v2.tb.travelapp.dto.WeatherResponse;
import dat22v2.tb.travelapp.service.RemoteApiService;
import dat22v2.tb.travelapp.service.TravelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/travel")
public class TravelController {



    RemoteApiService remoteApiService;
    TravelService travelService;

    public TravelController(TravelService travelService,
                            RemoteApiService remoteApiService) {
        this.travelService = travelService;
        this.remoteApiService = remoteApiService;
    }

    @GetMapping("/hey")
    public ResponseEntity<Boolean> test() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @PostMapping()
    public TravelResponse getTravel(@RequestBody TravelRequest body) {

        //check db



        return new TravelResponse();
    }

    @GetMapping("/weather/{location}")
    public Mono<WeatherResponse> getWeather(@PathVariable String location){
        return remoteApiService.atmosphereResponse(location);
    }
}
