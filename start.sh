#!/bin/bash
echo "compiling"
javac -d bin src/main/java/com/mahendra/test/*.java

echo "running"
java -cp "bin" src.main.java.com.mahendra.test.Main

echo "application closed"