package dat22v2.tb.travelapp.service;


import dat22v2.tb.travelapp.dto.TravelRequest;
import dat22v2.tb.travelapp.dto.TravelResponse;
import dat22v2.tb.travelapp.dto.WeatherResponse;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest.ChatGPTRequest;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptRequest.Message;
import dat22v2.tb.travelapp.dto.chatGPT.chatGptResponse.ChatGPTResponse;
import dat22v2.tb.travelapp.dto.openWeather.Weather;
import dat22v2.tb.travelapp.dto.openroute.DirectionResponse;
import dat22v2.tb.travelapp.dto.openroute.GeolocationResponse;
import dat22v2.tb.travelapp.exceptions.TravelException;
import dat22v2.tb.travelapp.repository.TravelRepository;
import dat22v2.tb.travelapp.utility.APIKeyHolder;
import dat22v2.tb.travelapp.utility.MonoApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class TravelService {

    TravelRepository travelRepository;

    RemoteApiService remoteApiService;

    @Autowired
    APIKeyHolder apiKeyHolder;


    public TravelService(TravelRepository travelRepository, RemoteApiService remoteApiService) {
        this.travelRepository = travelRepository;
        this.remoteApiService = remoteApiService;
    }

    final int KMCONVERSION = 1000;
    final int MINUTECONVERSION = 60;

    final double CELCIUSCONVERSION = -273.15;

   //Message user = new Message("user","The trip takes 4 hours, the distance is around 300 km, and the weather is sunny except for a stopping point at McDonalds, where it is rainy, this trip is between Copenhagen and Odense.");
    Message user = new Message("user","");
    ChatGPTRequest gptRequest = new ChatGPTRequest();


    public TravelResponse getChatGPTTourGuide(TravelRequest travelRequest){

        //Get the coordinates from the geolocations.
        GeolocationResponse startGeolocation = getGeolocation(travelRequest.getDeparture());
        GeolocationResponse endGeolocation = getGeolocation(travelRequest.getDestination());

        ArrayList<Double> startLatLong = startGeolocation.getFeatures().get(0).getGeometry().getCoordinates();
        //Converted into string, so that they can be used for the getTravelDetails()
        //String stringStartLatLong = String.format(Locale.US, "%f,%f", startLatLong.get(0), startLatLong.get(1));

        ArrayList<Double> endLatLong = endGeolocation.getFeatures().get(0).getGeometry().getCoordinates();
        //String stringEndLatLong = String.format(Locale.US, "%f,%f", endLatLong.get(0), endLatLong.get(1));

        //Get the travel distance, time it takes and even more details.
        DirectionResponse travel = getTravelDetails(startLatLong.get(0) + "," + startLatLong.get(1),endLatLong.get(0) + "," + endLatLong.get(1));

        //Get the weather from the two locations.
        WeatherResponse startWeather = remoteApiService.atmosphereResponse(startLatLong.get(0),startLatLong.get(1));
        WeatherResponse endWeather = remoteApiService.atmosphereResponse(endLatLong.get(0),endLatLong.get(1));

        //start location, destination, trip duration, trip distance, weather status start location, weather status destination, Language country Code
        //Formatting all the relevant information and putting it into a prompt.
        String startLocation = startGeolocation.getFeatures().get(0).getProperties().getLabel();
        String endLocation = endGeolocation.getFeatures().get(0).getProperties().getLabel();

        //Tripdistance is in meters, so it's converted into kilometers.
        double tripDistance = travel.getFeatures().get(0).getProperties().getSegments().get(0).getDistance();
        tripDistance /= KMCONVERSION;

        //Tripduration is in seconds, so it's converted into minutes.
        double tripDuration = travel.getFeatures().get(0).getProperties().getSegments().get(0).getDuration();
        tripDuration /= MINUTECONVERSION;

        //Getting the description and temperature in Kelvin, which will be converted to Celcius.
        String startWeatherDescription = startWeather.getWeather().get(0).getDescription();
        double startWeatherTemperature = startWeather.getMain().getTemp();
        startWeatherTemperature += CELCIUSCONVERSION;

        //Same here.
        String endWeatherDescription = endWeather.getWeather().get(0).getDescription();
        double endWeatherTemperature = endWeather.getMain().getTemp();
        endWeatherTemperature += CELCIUSCONVERSION;


        String finalPrompt = startLocation + "," + endLocation + "," + tripDistance + "," + tripDuration + "," + startWeatherDescription + " " + startWeatherTemperature + "," + endWeatherDescription + " " + endWeatherTemperature + "," + travelRequest.getLanguage();

        //Now we put the information into chatGPT.
        ChatGPTResponse chatGPTResponse = getChatGPTResponse(finalPrompt);

        TravelResponse travelResponse = new TravelResponse();

        travelResponse.setMessage(chatGPTResponse.getChoices().get(0).getMessage().getContent());

        return travelResponse;

    }


    public TravelResponse getTravel(TravelRequest body) throws TravelException {

        //TODO
        //the big boi
        //step1 body should contain departure and destination.
            //step 1.1 check database for this route and return it if information is less than 30? minutes old
            //step 1.2 Get geolocation for these
        //step2 get route for this geolocation
        //step3 find weather for the regions this route entails
        //step4 formulate the route and weather information
        //step5 ask chatGPT with information from step4
        //step6 formulate a response we can send back to user



        return null;
    }

    public GeolocationResponse getGeolocation(String location){

        String url = "https://api.openrouteservice.org/geocode/search?api_key=%s&text=%s";

        Mono<GeolocationResponse> test = MonoApiCaller.callGetApi(GeolocationResponse.class,url, apiKeyHolder.getOpenRouteAPIKey(),location);

        GeolocationResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }

    public DirectionResponse getTravelDetails(String startLatLong, String endLatLong){

        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=%s&start=%s&end=%s";

        Mono<DirectionResponse> test = MonoApiCaller.callGetApi(DirectionResponse.class,url,apiKeyHolder.getOpenRouteAPIKey(),startLatLong,endLatLong);

        DirectionResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }

    public ChatGPTResponse getChatGPTResponse(String prompt){

        user.setContent(prompt);
        gptRequest.addMessage(user);

        String url = "https://api.openai.com/v1/chat/completions";

        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization","Bearer " + apiKeyHolder.getChatGPTAPIKey());
        headers.put("Content-Type","application/json");

        Mono<ChatGPTResponse> test = MonoApiCaller.callTheNewPostApi(ChatGPTResponse.class,url,gptRequest,headers);

        ChatGPTResponse tmp = test.block();

        System.out.println(tmp);

        return tmp;
    }



}
