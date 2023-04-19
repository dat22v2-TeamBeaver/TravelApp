package dat22v2.tb.travelapp.dto.openroute;

import dat22v2.tb.travelapp.dto.openroute.geolocation.Feature;
import dat22v2.tb.travelapp.dto.openroute.geolocation.Geocoding;

import java.util.ArrayList;

public class GeolocationResponse {
    public Geocoding geocoding;
    public String type;
    public ArrayList<Feature> features;
    public ArrayList<Double> bbox;
}
