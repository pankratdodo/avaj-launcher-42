package simulator;

import aircraft.AircraftFactory;
import exception.ValidationException;

import java.io.*;

public class Simulator {

    public static PrintWriter writer;
    public static int times;
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
                    times = Integer.parseInt(line);
                    if (times < 0)
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
        catch (FileNotFoundException e)
        {
            throw new ValidationException("Scenario file is incorrect");
        }
    }

    public static void parseCoordinates(String longitude, String latitude, String height, String line) throws ValidationException {
        try {
            int longi = Integer.parseInt(longitude);
            int lat = Integer.parseInt(latitude);
            int hei = Integer.parseInt(height);
            if (longi < 0 || lat < 0 || hei < 0)
                throw new ValidationException("Coordinates must be positive number");
        }
        catch (NumberFormatException e)
        {
            throw new ValidationException("3, 4, 5 params if line " + line + " must be integer number");
        }
    }
    
    public static void createAircrafts(String line, BufferedReader reader) throws ValidationException, IOException {
        while (line != null)
        {
            splitLine = line.split(" ");
            if (splitLine.length != 5)
                throw new ValidationException("Line " + line + " must have 'TYPE NAME LONGITUDE LATITUDE HEIGHT'");
            AircraftFactory aircraftFactory = new AircraftFactory();
            parseCoordinates(splitLine[2], splitLine[3], splitLine[4], line);
            aircraftFactory.newAircraft(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]))
                    .registerTower(weatherTower);
            line = reader.readLine();
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

        for (int i = 0; i < times; i++)
        {
            Simulator.writer.println("------------Simulation time #" + (i + 1) + "--------------");
            weatherTower.changeWeather();
        }
        writer.close();
    }
}
