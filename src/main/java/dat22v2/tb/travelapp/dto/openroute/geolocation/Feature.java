package dat22v2.tb.travelapp.dto.openroute.geolocation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Feature {
    public String type;
    public Geometry geometry;
    public Properties properties;
}
