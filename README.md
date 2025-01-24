### Description
This program loads a file containing city connections and determines if two cities are connected via direct or indirect routes using a graph-based approach.

### Instructions for Running
1. **Compile**: 
   ```bash
   javac ConnectedCities.java
   ```
2. **Run**:
   ```bash
   java ConnectedCities <fileName> <city1> <city2>
   ```
   Example:
   ```bash
   java ConnectedCities connections.txt London Birmingham
   ```

### Testing
Unit tests are provided using JUnit. To run:
1. Add `ConnectedCitiesTest.java` to your test folder.
2. Run the tests using your IDE or a build tool like Maven/Gradle.

---

## Racing Simulation
### Description
This program simulates multiple races between vehicles with varying characteristics (speed and reliability). Results are printed for each vehicle.

### Instructions for Running
1. **Compile**:
   ```bash
   javac RacingSimulation.java
   ```
2. **Run**:
   ```bash
   java RacingSimulation <number_of_races> <distance>
   ```
   Example:
   ```bash
   java RacingSimulation 10 500
   ```

---

## REST Echo Service
### Description
A Spring Boot application that provides a REST endpoint to echo a message sent via HTTP POST or GET request, along with a received timestamp.

### Prerequisites
- Ensure Java 17+ and Maven are installed.
- Spring Boot dependencies are managed through `pom.xml`.

### Instructions for Running
1. **Run Application**:
   ```bash
   mvn spring-boot:run
   ```
2. **Usage**:
   - **POST**:
     ```bash
     curl -X POST -H "Content-Type: application/json" -d '{"message": "Hello"}' http://localhost:8080/echo
     ```
   - **GET**:
     ```bash
     curl "http://localhost:8080/echo?message=Hello"
     ```

### Example Response
```json
{
  "message": "Hello",
  "timestamp": "2025-01-24T10:00:00Z"
}
```

---