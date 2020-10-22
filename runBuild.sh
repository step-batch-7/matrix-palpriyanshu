rm -rf out/*
echo "Class-Path: lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar \n" > manifest_$1.txt

javac -d out -cp lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar $(find . -name "*.java")
jar cfm $1.jar manifest_$1.txt -C out .
java -jar $1.jar