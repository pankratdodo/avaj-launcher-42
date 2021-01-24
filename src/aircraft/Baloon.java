package aircraft;

import simulator.Simulator;
import simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weatherNow = this.weatherTower.getWeather(coordinates);
        if (weatherNow.equalsIgnoreCase("sun"))
        {
            coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
            Simulator.writer.println("Baloon#" + name + "(" + id + "): sun is cool");
        }
        else if (weatherNow.equalsIgnoreCase("rain"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
            Simulator.writer.println("Baloon#" + name + "(" + id + "): rain is wet");
        }
        else if (weatherNow.equalsIgnoreCase("fog"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
            Simulator.writer.println("Baloon#" + name + "(" + id + "): fog, i cant see");
        }
        else if (weatherNow.equalsIgnoreCase("snow"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
            Simulator.writer.println("Baloon#" + name + "(" + id + "): snow, im falling");
        }
        if (coordinates.getHeight() == 0)
        {
            Simulator.writer.println("Baloon#" + name + "(" + id + "): landing");
            weatherTower.unregister(this);
            Simulator.writer.println("Tower says: Baloon#" + name + "(" + id + "):  unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Baloon" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
