package aircraft;

import exception.ValidationException;

public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws ValidationException {
        if (type.equalsIgnoreCase("baloon"))
            return new Baloon(name, new Coordinates(longitude, latitude, height));
        else if (type.equalsIgnoreCase("helicopter"))
            return new Helicopter(name, new Coordinates(longitude, latitude, height));
        else if (type.equalsIgnoreCase("jetplane"))
            return new JetPlane(name, new Coordinates(longitude, latitude, height));
        throw new ValidationException("Invalid type of aircraft " + type);
    }
}
