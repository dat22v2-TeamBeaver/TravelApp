package dat22v2.tb.travelapp.dto.openroute.geolocation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
@Getter
@Setter
@NoArgsConstructor
public class Engine {
    public String name;
    public String author;
    public String version;
}
