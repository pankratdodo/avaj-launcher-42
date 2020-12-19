public class Baloon extends Aircraft implements Flyable{

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
            System.out.println("Baloon#" + name + "(" + id + "): sun is cool");
        }
        else if (weatherNow.equalsIgnoreCase("rain"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
            System.out.println("Baloon#" + name + "(" + id + "): rain is wet");
        }
        else if (weatherNow.equalsIgnoreCase("fog"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
            System.out.println("Baloon#" + name + "(" + id + "): fog, i cant see");
        }
        else if (weatherNow.equalsIgnoreCase("snow"))
        {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
            System.out.println("Baloon#" + name + "(" + id + "): snow, im falling");
        }
        if (coordinates.getHeight() == 0)
        {
            System.out.println("Baloon#" + name + "(" + id + "): landing");
            weatherTower.unregister(this);
            System.out.println("Tower says: Baloon#" + name + "(" + id + "):  unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: Baloon" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
