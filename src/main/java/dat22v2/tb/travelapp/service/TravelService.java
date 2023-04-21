package dat22v2.tb.travelapp.service;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
import dat22v2.tb.travelapp.dto.WeatherResponse;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest.ChatGPTRequest;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest.Message;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptResponse.ChatGPTResponse;
import dat22v2.tb.travelapp.dto.openroute.DirectionResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.repository.TravelRepository;
import dat22v2.tb.travelapp.utility.APIKeyHolder;
import dat22v2.tb.travelapp.utility.Convert;
import dat22v2.tb.travelapp.utility.MonoApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class TravelService {

    TravelRepository travelRepository;
    RemoteApiService remoteApiService;


    public TravelService(TravelRepository travelRepository, RemoteApiService remoteApiService) {
        this.travelRepository = travelRepository;
        this.remoteApiService = remoteApiService;
    }


    public TravelResponse getChatGPTTourGuide(TravelRequest travelRequest){
        //TODO - API spam use prevention with DB caching
        // save travels with a "lastViewedDate".
        // if lastViewedDate is within decided time interval, then return the saved travel found in DB
        // else run the API's

        //Get the coordinates from the geolocations.
        GeolocationResponse departureGeolocation = remoteApiService.getGeolocationResponse(travelRequest.getDeparture());
        GeolocationResponse destinationGeolocation = remoteApiService.getGeolocationResponse(travelRequest.getDestination());

        ArrayList<Double> departureLatLong = departureGeolocation.getFeatures().get(0).getGeometry().getCoordinates();
        String departureLatLongString = departureLatLong.get(0) + "," + departureLatLong.get(1);
        //Converted into string, so that they can be used for the getTravelDetails()
        //String stringStartLatLong = String.format(Locale.US, "%f,%f", startLatLong.get(0), startLatLong.get(1));

        ArrayList<Double> destinationLatLong = destinationGeolocation.getFeatures().get(0).getGeometry().getCoordinates();
        String destinationLatLongString = destinationLatLong.get(0) + "," + destinationLatLong.get(1);
        //String stringEndLatLong = String.format(Locale.US, "%f,%f", endLatLong.get(0), endLatLong.get(1));

        //Get the travel distance, time it takes and even more details.
        DirectionResponse travel = remoteApiService.getDirectionResponse(departureLatLongString,destinationLatLongString);

        //Get the weather from the two locations.
        WeatherResponse departureWeather = remoteApiService.atmosphereResponse(departureLatLong.get(0),departureLatLong.get(1));
        WeatherResponse destinationWeather = remoteApiService.atmosphereResponse(destinationLatLong.get(0),destinationLatLong.get(1));

        //start location, destination, trip duration, trip distance, weather status start location, weather status destination, Language country Code
        //Formatting all the relevant information and putting it into a prompt.
        String startLocation = departureGeolocation.getFeatures().get(0).getProperties().getLabel();
        String endLocation = destinationGeolocation.getFeatures().get(0).getProperties().getLabel();

        //Tripdistance is in meters, so it's converted into kilometers.
        double tripDistance = Convert.metersToKm(travel.getFeatures().get(0).getProperties().getSegments().get(0).getDistance());

        //Tripduration is in seconds, so it's converted into minutes.
        double tripDuration = Convert.secondsToMinutes(travel.getFeatures().get(0).getProperties().getSegments().get(0).getDuration());

        //Getting the description and temperature in Kelvin for departure weather, which will be converted to Celcius.
        String departureWeatherDescription = departureWeather.getWeather().get(0).getDescription();
        double departureWeatherTemperature = Convert.kelvinToCelsius(departureWeather.getMain().getTemp());

        //Getting the description and temperature in Kelvin for destination weather, which will be converted to Celcius.
        String endWeatherDescription = destinationWeather.getWeather().get(0).getDescription();
        double endWeatherTemperature = Convert.kelvinToCelsius(destinationWeather.getMain().getTemp());

        String finalPrompt = startLocation + ","
            + endLocation + ","
            + tripDistance + ","
            + tripDuration + ","
            + departureWeatherDescription + " "
            + departureWeatherTemperature + ","
            + endWeatherDescription + " "
            + endWeatherTemperature + ","
            + travelRequest.getLanguage();

        //Now we put the information into chatGPT.

        ChatGPTRequest gptRequest = new ChatGPTRequest();
        Message userMessage = new Message("user",finalPrompt);
        gptRequest.addMessage(userMessage);
        ChatGPTResponse chatGPTResponse = remoteApiService.getChatGPTResponse(gptRequest);


        TravelResponse travelResponse = new TravelResponse();
        travelResponse.setMessage(chatGPTResponse.getChoices().get(0).getMessage().getContent());

        return travelResponse;

    }



}
