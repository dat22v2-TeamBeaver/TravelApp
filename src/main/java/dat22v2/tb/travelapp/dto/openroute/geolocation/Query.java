package dat22v2.tb.travelapp.dto.openroute.geolocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Query {
    public String text;
    public int size;
    @JsonProperty("private")
    public boolean myprivate;
    public Lang lang;
    public int querySize;
    public String parser;
    public ParsedText parsed_text;
}
