package dat22v2.tb.travelapp.dto.openroute.direction;

import java.util.ArrayList;

public class Step {
    public double distance;
    public double duration;
    public int type;
    public String instruction;
    public String name;
    public ArrayList<Integer> way_points;
    public int exit_number;
}
