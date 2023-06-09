package dat22v2.tb.travelapp.dto;

import dat22v2.tb.travelapp.dto.openWeather.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    public Coord coord;
    public ArrayList<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;

    public static Mono<WeatherResponse> fromMono(Mono<WeatherResponse> mono) {
        return mono.map(response -> new WeatherResponse(
                response.getCoord(),
                response.getWeather(),
                response.getBase(),
                response.getMain(),
                response.getVisibility(),
                response.getWind(),
                response.getClouds(),
                response.getDt(),
                response.getSys(),
                response.getTimezone(),
                response.getId(),
                response.getName(),
                response.getCod()
        ));
    }
}
