rm -rf out/*
javac -d out -cp lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar $(find . -name "*.java")
java -cp out:lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore com.step.math.MatrixTest