import java.io.*;

public class Simulator {

    public static FileWriter writer;
    public static void main(String[] av) throws IOException {

        int cycles;
        String[] splitLine;
        WeatherTower weatherTower = new WeatherTower();

        if (av.length != 1)
            throw new RuntimeException("Add a one scenario file");
        String scenarioFile = av[0];
        try {
            writer = new FileWriter("simulation.txt");
        }
        catch (FileNotFoundException exception)
        {
            throw new RuntimeException("Simulation file is incorrect");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(scenarioFile));
            String line = reader.readLine();
            if (line != null)
            {
                try {
                    cycles = Integer.parseInt(line);
                    if (cycles < 0)
                        throw new RuntimeException("First line in scenario file must be positive number");
                    line = reader.readLine();
                }
                catch (NumberFormatException e)
                {
                    throw new RuntimeException("First line in scenario file must be integer number");
                }
            }
            else
                throw new RuntimeException("File is empty");
            while (line != null)
            {
                splitLine = line.split(" ");
                if (splitLine.length != 5)
                    throw new RuntimeException("Line " + line + " must have 'TYPE NAME LONGITUDE LATITUDE HEIGHT'");
//                System.out.println(line);
                line = reader.readLine();
                AircraftFactory aircraftFactory = new AircraftFactory();
                try {
                    aircraftFactory.newAircraft(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4])).registerTower(weatherTower);
                }
                catch (NumberFormatException e)
                {
                    throw new RuntimeException("3, 4, 5 params if line " + line + " must be integer number");
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("Scenario file is incorrect");
        }

        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        while (cycles > 0)
        {
            weatherTower.changeWeather();
            cycles--;
        }
        writer.close();
    }
}
