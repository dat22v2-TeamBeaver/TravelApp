package dat22v2.tb.travelapp.dto.openroute;

import dat22v2.tb.travelapp.dto.openroute.geolocation.Feature;
import dat22v2.tb.travelapp.dto.openroute.geolocation.Geocoding;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class GeolocationResponse { //koordinater til overalt i verden
    public Geocoding geocoding;
    public String type;
    public ArrayList<Feature> features;
    public ArrayList<Double> bbox;
}
