package simulator;

import aircraft.Coordinates;

public class WeatherProvider {

    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherProvider() {}

    public static WeatherProvider getProvider()
    {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates)
    {
        int res = (coordinates.getLongitude() * coordinates.getLatitude() * coordinates.getHeight() / (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight())) % 4;
        return weather[res % 4];
    }
}
