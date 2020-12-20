import java.io.*;

public class Simulator {

    public static PrintWriter writer;
    public static int cycles;
    public  static String[] splitLine;
    public static WeatherTower weatherTower = new WeatherTower();

    public static void readFile(String scenarioFile) throws IOException, ValidationException
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(scenarioFile));
            String line = reader.readLine();
            if (line != null)
            {
                try {
                    cycles = Integer.parseInt(line);
                    if (cycles < 0)
                        throw new ValidationException("First line in scenario file must be positive number");
                    line = reader.readLine();
                }
                catch (NumberFormatException e)
                {
                    throw new ValidationException("First line in scenario file must be integer number");
                }
            }
            else
                throw new ValidationException("File is empty");
            createAircrafts(line, reader);
            reader.close();
        }
        catch (FileNotFoundException | ValidationException e)
        {
            throw new ValidationException("Scenario file is incorrect");
        }
    }

    public static void createAircrafts(String line, BufferedReader reader) throws ValidationException, IOException {
        while (line != null)
        {
            splitLine = line.split(" ");
            if (splitLine.length != 5)
                throw new ValidationException("Line " + line + " must have 'TYPE NAME LONGITUDE LATITUDE HEIGHT'");
            line = reader.readLine();
            AircraftFactory aircraftFactory = new AircraftFactory();
            try {
                aircraftFactory.newAircraft(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]))
                        .registerTower(weatherTower);
            }
            catch (NumberFormatException | IOException e)
            {
                throw new ValidationException("3, 4, 5 params if line " + line + " must be integer number");
            }
        }
    }

    public static void main(String[] av) throws ValidationException, IOException {

        if (av.length != 1)
            throw new ValidationException("Add a one scenario file");
        String scenarioFile = av[0];
        try {
            writer = new PrintWriter("simulation.txt");
        }
        catch (FileNotFoundException exception)
        {
            throw new ValidationException("Simulation file is incorrect");
        }
        readFile(scenarioFile);

        while (cycles > 0)
        {
            weatherTower.changeWeather();
            cycles--;
        }
        writer.close();
    }
}
