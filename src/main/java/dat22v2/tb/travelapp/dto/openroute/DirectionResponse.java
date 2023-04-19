package dat22v2.tb.travelapp.dto.openroute;

import dat22v2.tb.travelapp.dto.openroute.direction.Feature;
import dat22v2.tb.travelapp.dto.openroute.direction.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class DirectionResponse {
    public String type;
    public ArrayList<Feature> features;
    public ArrayList<Double> bbox;
    public Metadata metadata;
}

