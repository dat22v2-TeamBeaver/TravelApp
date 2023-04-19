package dat22v2.tb.travelapp.dto.openWeather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather{
    public int id;
    public String main;
    public String description;
    public String icon;
}
