# String to Xml Parsing
Java program that can convert text into either XML formatted data or comma separated (CSV) data. The text is to be parsed, broken into sentences and words and the words have to be sorted.
## Local Build
How to create uber jar
```
mvn clean compile assembly:single
```

## Unit Test Execution
```
mvn clean package
```
![Screenshot](TestCase.png)

## Execution
To execute locally, use the following command
```
java -jar ./target/ParserAssginment-1.0-SNAPSHOT-jar-with-dependencies.jar ./src/main/resources/small.in /Users/keshavjha/Downloads/output.xml
```

or use run.sh for build and execution

```
./run.sh
```



## Execution Configuration for TextToXML.java
![Screenshot](TextToXMLConfg.png)




