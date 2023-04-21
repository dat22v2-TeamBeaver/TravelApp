package dat22v2.tb.travelapp.utility;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class MonoApiCaller {

    public static <T> Mono<T> callGetApi(Class<T> responseType, String url, Object... urlParams) {
        String fullUri = String.format(url, urlParams);
        WebClient client = WebClient.create();
        return client.get()
            .uri(fullUri)
            .retrieve()
            .bodyToMono(responseType);
    }


    public static <T> Mono<T> callPostApi(Class<T> responseType, String url, Object body, Map<String, String> headers) {
        WebClient client = WebClient.builder()
                .defaultHeaders(httpHeader -> headers.forEach((httpHeader::add))).build();
        return client.post()
                .uri(url)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(responseType);
    }
}
