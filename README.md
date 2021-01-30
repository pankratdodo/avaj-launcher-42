# avaj-launcher
Since the software will run on a multitude of operating systems in a very strict enterprise environement, he decides to use a classic Object-Oriented language: Java.
## Installation
1. Download/Clone this repo
```
git clone https://github.com/pankratdodo/avaj-launcher-42.git
```
2. `cd` into directrory and run `run.sh`
```
cd avaj-launcher-42/src/ && sh run.sh scenario.txt
```
## Main project instructions
### General Instructions
- You are allowed to use language features up to Java 7 included.
- You are not allowed to use any external libraries, build tools or code generators.
- Do not use the default package.
- Create your own relevant packages following the Java package naming conventions.
- Java is compiled into an intermediate language. This will generate some .class files.
- Make sure you have javac and java available as commands in your terminal.

![](avaj_uml.jpg)
### Mandatory part
You need to implement an aircraft simulation program based on the class diagram provided to you. All classes are required to be implemented respecting every detail provided in the diagram. Feel free to add more classes or include additional attributes if you think it is necessary, but do not change access modifiers or the class hierarchy for the classes provided in the diagram.
#### Program behaviour
Program will take one and only one argument from the command line. This argument represents the name of a text file that will contain the scenario that needs to be simulated. You can find an example file provided with the subject.
Executing the program will generate a file simulation.txt that describes the outcome of the simulation.
#### Scenario file
The first line of the file contains a positive integer number. This number represents the number of times the simulation is run. In our case, this will be the number of times a weather change is triggered.
Each following line describes an aircraft that will be part of the simulation, with this format: `TYPE NAME LONGITUDE LATITUDE HEIGHT`.
#### Weather generation
There are 4 types of weather:
- `RAIN`
- `FOG`
- `SUN`
- `SNOW`

Each 3 dimensional point has its own weather. Feel free to use whatever generation algorithm you want, as long as it takes into account the point’s coordinates.
#### Simulation
- Coordinates are positive numbers.
- The height is in the 0-100 range.
- If an aircraft needs to pass the upper limit height it remains at 100.
- Each time an aircraft is created, it receives a unique ID. There can’t be 2 aircrafts with the same ID.
- If an aircraft reaches height 0 or needs to go below it, the aircraft lands, unregisters from the weather tower and logs its current coordinates.
- When a weather change occurs, each aircraft type needs to log a message, as seen in the example. The message format is: `TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE`.
A funny message will be appreciated during the correction.
- Each time an aircraft registers or unregisters to/from the weather tower, a message will be logged.
#### Validation
The input file needs to be validated. Any abnormal behaviour due to invalid input data is not acceptable. If the input file data is not correct the program stops execution. Any error messages will be printed to the standard output.
### Bonus part
- Create my own custom exceptions for treating abnormal behaviour.
