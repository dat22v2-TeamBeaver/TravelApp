package dat22v2.tb.travelapp.service;


import dat22v2.tb.travelapp.repository.TravelRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelService {

    TravelRepository travelRepository;


    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }





}
