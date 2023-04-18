package dat22v2.tb.travelapp.api;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
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




    @PostMapping()
    public TravelResponse getTravel(@RequestBody TravelRequest body) {

        //check db



        return new TravelResponse();
    }
}
