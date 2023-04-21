package dat22v2.tb.travelapp.dto.openroute.direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Segment {
    public double distance;
    public double duration;
    public ArrayList<Step> steps;
}
