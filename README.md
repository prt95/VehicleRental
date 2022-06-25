## Building the application using maven

You can build and package the application in the form of a jar file using maven -

```sh
cd VehicleRental
mvn clean package
```

## Running the application

You can run the jar file created by the mvn package command like so

`java -jar target/application.jar`

## Running tests

The `mvn package` command runs all the unit tests and also packages the application in the form of a jar file. If you
just want to run the tests without packaging it, then you can use `mvn test` command.

## Coverage
Covers more that 50% of lines.
