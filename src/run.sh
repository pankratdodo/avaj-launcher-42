rm -rf simulation.txt
find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java Simulator $@
rm -rf *.class
rm -rf sources.txt