package dat22v2.tb.travelapp.dto.openroute.direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Step {
    public double distance;
    public double duration;
    public int type;
    public String instruction;
    public String name;
    public ArrayList<Integer> way_points;
    public int exit_number;
}
