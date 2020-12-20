rm -rf simulation.txt
javac -d . src/*.java
java -cp . Simulator $@
rm -rf *.class