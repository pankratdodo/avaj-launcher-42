public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        if (type.equalsIgnoreCase("baloon"))
            new Baloon(name, new Coordinates(longitude, latitude, height));
        else if (type.equalsIgnoreCase("helicopter"))
            new Helicopter(name, new Coordinates(longitude, latitude, height));
        else if (type.equalsIgnoreCase("jetplane"))
            new JetPlane(name, new Coordinates(longitude, latitude, height));
        return null;
    }
}
