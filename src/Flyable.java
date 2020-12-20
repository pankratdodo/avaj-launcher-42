import java.io.IOException;

public interface Flyable {

    void updateConditions();

    void registerTower(WeatherTower weatherTower) throws IOException;
}
