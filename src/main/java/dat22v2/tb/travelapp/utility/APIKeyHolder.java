package dat22v2.tb.travelapp.utility;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class APIKeyHolder {

    @Value("${OPEN_ROUTE_API_KEY}")
    @Getter
    private String openRouteAPIKey;
    @Value("${CHATGPT_API_KEY}")
    @Getter
    private String chatGPTAPIKey;
    @Value("${WEATHER_API_KEY}")
    @Getter
    private String weatherAPIKey;





}
