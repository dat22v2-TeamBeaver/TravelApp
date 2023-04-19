package dat22v2.tb.travelapp.dto.openroute.direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Properties {
    public ArrayList<Segment> segments;
    public Summary summary;
    public ArrayList<Integer> way_points;
}
