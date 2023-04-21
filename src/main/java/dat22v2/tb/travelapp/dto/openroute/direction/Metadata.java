package dat22v2.tb.travelapp.dto.openroute.direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Metadata {
    public String attribution;
    public String service;
    public long timestamp;
    public Query query;
    public Engine engine;
}
