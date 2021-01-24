rm -rf simulation.txt
find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java simulator/Simulator $@
rm -rf aircraft/*.class exception/*.class simulator/*.class
rm -rf sources.txt