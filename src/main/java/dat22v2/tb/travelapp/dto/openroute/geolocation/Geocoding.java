package dat22v2.tb.travelapp.dto.openroute.geolocation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Geocoding {
    public String version;
    public String attribution;
    public Query query;
    public Engine engine;
    public long timestamp;
}
