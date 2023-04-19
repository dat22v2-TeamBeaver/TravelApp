package dat22v2.tb.travelapp.dto.openroute.geolocation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
public class Geometry {
    public String type;
    public ArrayList<Double> coordinates;
}
