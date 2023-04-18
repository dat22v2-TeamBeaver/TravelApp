package dat22v2.tb.travelapp.dto.openroute.geolocation;

import java.util.ArrayList;

public class Root {
    public Geocoding geocoding;
    public String type;
    public ArrayList<Feature> features;
    public ArrayList<Double> bbox;
}
