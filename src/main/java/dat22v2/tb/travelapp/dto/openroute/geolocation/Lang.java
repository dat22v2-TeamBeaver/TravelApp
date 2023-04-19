package dat22v2.tb.travelapp.dto.openroute.geolocation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lang {
    public String name;
    public String iso6391;
    public String iso6393;
    public String via;
    public boolean defaulted;
}
