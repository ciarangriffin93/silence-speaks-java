import java.util.*;

// Abstract base class for all vehicles (Motorcycle, Car, Truck)
abstract class Vehicle {
    String name;
    int speed; // Speed in km/h
    int acceleration; // Acceleration in m/s^2

    // Constructor to initialize name, speed, and acceleration
    public Vehicle(String name, int speed, int acceleration) {
        this.name = name;
        this.speed = speed;
        this.acceleration = acceleration;
    }

    // Abstract method to calculate the performance score of the vehicle (higher is better)
    abstract int getPerformanceScore();
}

// Motorcycle class extends Vehicle and implements getPerformanceScore
class Motorcycle extends Vehicle {
    public Motorcycle() {
        super("Motorcycle", 150, 6); // Speed: 150 km/h, Acceleration: 6 m/s^2
    }

    // Calculate performance score for the motorcycle (speed + acceleration * factor)
    @Override
    int getPerformanceScore() {
        return speed + (acceleration * 5); // Motorcycle performance metric
    }
}

// Car class extends Vehicle and implements getPerformanceScore
class Car extends Vehicle {
    public Car() {
        super("Car", 200, 4); // Speed: 200 km/h, Acceleration: 4 m/s^2
    }

    // Calculate performance score for the car (speed + acceleration * factor)
    @Override
    int getPerformanceScore() {
        return speed + (acceleration * 3); // Car performance metric
    }
}

// Truck class extends Vehicle and implements getPerformanceScore
class Truck extends Vehicle {
    public Truck() {
        super("Truck", 120, 2); // Speed: 120 km/h, Acceleration: 2 m/s^2
    }

    // Calculate performance score for the truck (speed + acceleration * factor)
    @Override
    int getPerformanceScore() {
        return speed + (acceleration * 2); // Truck performance metric
    }
}

// Main RaceSimulator class that runs the simulation
public class RaceSimulator {
    private static final List<Vehicle> vehicles = new ArrayList<>(); // List to hold vehicles

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RaceSimulator <number_of_races>");
            return;
        }

        // Parse the number of races to simulate from command-line argument
        int numberOfRaces = Integer.parseInt(args[0]);

        // Initialize the vehicles (Motorcycle, Car, Truck)
        vehicles.add(new Motorcycle());
        vehicles.add(new Car());
        vehicles.add(new Truck());

        // Run the simulation with the given number of races
        simulateRaces(numberOfRaces);
    }

    // Method to simulate the races
    private static void simulateRaces(int numberOfRaces) {
        Map<String, Integer> raceResults = new HashMap<>(); // Store results (wins) of each vehicle
        
        // Simulate N races
        for (int i = 1; i <= numberOfRaces; i++) {
            System.out.println("\nRace " + i + " Results:");
            Vehicle winner = simulateRace(); // Run a race and get the winner
            raceResults.put(winner.name, raceResults.getOrDefault(winner.name, 0) + 1); // Update the win count
            System.out.println("Winner: " + winner.name + "\n");
        }

        // After all races, print the overall winner
        String overallWinner = getOverallWinner(raceResults);
        System.out.println("\nOverall Winner after " + numberOfRaces + " races: " + overallWinner);
    }

    // Method to simulate a single race and return the winning vehicle
    private static Vehicle simulateRace() {
        Vehicle winner = null;
        int highestScore = Integer.MIN_VALUE; // Track the highest performance score

        // Compare all vehicles' performance scores to determine the winner
        for (Vehicle vehicle : vehicles) {
            int score = vehicle.getPerformanceScore();
            System.out.println(vehicle.name + " Score: " + score);

            if (score > highestScore) {
                highestScore = score;
                winner = vehicle; // Update winner if this vehicle has the highest score
            }
        }

        return winner; // Return the vehicle with the highest score
    }

    // Method to determine the vehicle with the most wins
    private static String getOverallWinner(Map<String, Integer> raceResults) {
        String overallWinner = null;
        int maxWins = 0;

        // Find the vehicle with the most wins
        for (Map.Entry<String, Integer> entry : raceResults.entrySet()) {
            if (entry.getValue() > maxWins) {
                maxWins = entry.getValue();
                overallWinner = entry.getKey();
            }
        }

        return overallWinner; // Return the overall winner's name
    }
}