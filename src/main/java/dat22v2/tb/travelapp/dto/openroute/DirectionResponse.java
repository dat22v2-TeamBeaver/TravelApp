package dat22v2.tb.travelapp.dto.openroute;

import dat22v2.tb.travelapp.dto.openroute.direction.Feature;
import dat22v2.tb.travelapp.dto.openroute.direction.Metadata;

import java.util.ArrayList;

public class DirectionResponse {
    public String type;
    public ArrayList<Feature> features;
    public ArrayList<Double> bbox;
    public Metadata metadata;
}
