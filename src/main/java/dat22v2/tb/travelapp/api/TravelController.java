package dat22v2.tb.travelapp.api;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
import dat22v2.tb.travelapp.dto.chatGptResponse.ChatGPTResponse;
import dat22v2.tb.travelapp.dto.openroute.DirectionResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.dto.WeatherResponse;
import dat22v2.tb.travelapp.service.RemoteApiService;
import dat22v2.tb.travelapp.exceptions.TravelException;
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

    @GetMapping("/findGeo")
    public GeolocationResponse findGeolocation(@RequestParam String location) {
        return travelService.getGeolocation(location);
    }

    @GetMapping("/findTravel")
    public DirectionResponse findTravel(@RequestParam String start, @RequestParam String end) {
        return travelService.getTravelDetails(start,end);
    }

    @GetMapping("/gpt")
    public ChatGPTResponse gptResponse() {
        return travelService.getChatGPTResponse();
    }

    @PostMapping()
    public ResponseEntity<TravelResponse> getTravel(@RequestBody TravelRequest body) throws TravelException {
        TravelResponse response = travelService.getTravel(body);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/weather/{location}")
    public WeatherResponse getWeather(@PathVariable String location){
        return remoteApiService.atmosphereResponse(location);
    }
}
