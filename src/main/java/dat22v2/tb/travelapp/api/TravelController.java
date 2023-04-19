package dat22v2.tb.travelapp.api;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
import dat22v2.tb.travelapp.dto.openroute.DirectionResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.exceptions.TravelException;
import dat22v2.tb.travelapp.service.TravelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/travel")
public class TravelController {


    TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
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

    @PostMapping()
    public ResponseEntity<TravelResponse> getTravel(@RequestBody TravelRequest body) throws TravelException {
        TravelResponse response = travelService.getTravel(body);
        return ResponseEntity.ok(response);
    }
}
