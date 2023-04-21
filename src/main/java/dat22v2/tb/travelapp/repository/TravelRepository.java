package dat22v2.tb.travelapp.repository;

import dat22v2.tb.travelapp.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Integer> {


    public boolean existsByName(String name);



}
