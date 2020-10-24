rm -rf out/*
javac -d out -cp lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar $(find . -name "*.java")