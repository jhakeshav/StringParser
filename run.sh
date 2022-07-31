mvn clean compile assembly:single
# Use the argumentas for input, output and format for execution
#java -jar ./target/ParserAssginment-1.0-SNAPSHOT-jar-with-dependencies.jar <INPUT_FILE> <OUTPUT_FILE> <FORMAT>
java -jar ./target/ParserAssginment-1.0-SNAPSHOT-jar-with-dependencies.jar ./src/main/resources/small.in ~/output.xml xml
#java -jar ./target/ParserAssginment-1.0-SNAPSHOT-jar-with-dependencies.jar ./src/main/resources/small.in ~/output.csv csv