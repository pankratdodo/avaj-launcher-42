public class JetPlane extends Aircraft implements Flyable{

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weatherNow = this.weatherTower.getWeather(coordinates);
        if (weatherNow.equalsIgnoreCase("sun"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
            Simulator.writer.println("JetPlane#" + name + "(" + id + "): sun is cool");
        }
        else if (weatherNow.equalsIgnoreCase("rain"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
            Simulator.writer.println("JetPlane#" + name + "(" + id + "): rain is wet");
        }
        else if (weatherNow.equalsIgnoreCase("fog"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
            Simulator.writer.println("JetPlane#" + name + "(" + id + "): fog, i cant see");
        }
        else if (weatherNow.equalsIgnoreCase("snow"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
            Simulator.writer.println("JetPlane#" + name + "(" + id + "): snow, im falling");
        }
        if (coordinates.getHeight() == 0)
        {
            Simulator.writer.println("JetPlane#" + name + "(" + id + "): landing");
            weatherTower.unregister(this);
            Simulator.writer.println("Tower says: JetPlane#" + name + "(" + id + "):  unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: JetPlane" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
