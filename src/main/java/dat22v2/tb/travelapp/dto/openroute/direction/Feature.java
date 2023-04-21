package dat22v2.tb.travelapp.dto.openroute.direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Feature {
    public ArrayList<Double> bbox;
    public String type;
    public Properties properties;
    public Geometry geometry;
}
