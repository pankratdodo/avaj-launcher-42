package aircraft;

import simulator.Simulator;
import simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    @Override
    public void updateConditions()
    {
        String weatherNow = this.weatherTower.getWeather(coordinates);
        if (weatherNow.equalsIgnoreCase("sun"))
        {
            coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
            Simulator.writer.println("Helicopter#" + name + "(" + id + "): sun is cool");
        }
        else if (weatherNow.equalsIgnoreCase("rain"))
        {
            coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
            Simulator.writer.println("Helicopter#" + name + "(" + id + "): rain is wet");
        }
        else if (weatherNow.equalsIgnoreCase("fog"))
        {
            coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
            Simulator.writer.println("Helicopter#" + name + "(" + id + "): fog, i cant see");
        }
        else if (weatherNow.equalsIgnoreCase("snow"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
            Simulator.writer.println("Helicopter#" + name + "(" + id + "): snow, im falling");
        }
        if (coordinates.getHeight() == 0)
        {
            Simulator.writer.println("Helicopter#" + name + "(" + id + "): landing");
            weatherTower.unregister(this);
            Simulator.writer.println("Tower says: Helicopter#" + name + "(" + id + "):  unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Helicopter" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
