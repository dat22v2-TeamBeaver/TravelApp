package dat22v2.tb.travelapp.utility;

public class Convert {



    static public double kelvinToCelsius(double kelvin) {
        final double KELVIN_TO_CELSIUS = -273.15;
        return kelvin + KELVIN_TO_CELSIUS;
    }

    static public double secondsToMinutes(double seconds) {
        final int SECONDS_PER_MINUTE = 60;
        return seconds/SECONDS_PER_MINUTE;
    }


    static public double metersToKm(double meters) {
        final int METERS_PER_KILOMETER = 1000;
        return meters/METERS_PER_KILOMETER;
    }



}
