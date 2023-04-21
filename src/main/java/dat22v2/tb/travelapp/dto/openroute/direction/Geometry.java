package dat22v2.tb.travelapp.dto.openroute.direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Geometry {
    public ArrayList<ArrayList<Double>> coordinates;
    public String type;
}
