package dat22v2.tb.travelapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelRequest {
    String departure;
    String destination;
    String language;

}
