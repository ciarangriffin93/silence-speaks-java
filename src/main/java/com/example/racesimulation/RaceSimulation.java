package com.example.racesimulation;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

abstract class Vehicle {
    private final String name;
    private final double maxSpeed; // Maximum speed in km/h
    private final double acceleration; // Acceleration in km/h per second

    public Vehicle(String name, double maxSpeed, double acceleration) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
    }

    public String getName() {
        return name;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    // Simulate the time taken to finish a race of given distance
    public double raceTime(double distance) {
        // Basic formula: t = sqrt(2 * distance / acceleration) + distance / maxSpeed
        return Math.sqrt(2 * distance / acceleration) + distance / maxSpeed;
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String name, double maxSpeed, double acceleration) {
        super(name, maxSpeed, acceleration);
    }
}

class Car extends Vehicle {
    public Car(String name, double maxSpeed, double acceleration) {
        super(name, maxSpeed, acceleration);
    }
}

class Truck extends Vehicle {
    public Truck(String name, double maxSpeed, double acceleration) {
        super(name, maxSpeed, acceleration);
    }
}

public class RaceSimulation {

    public static void main(String[] args) {
        // Create vehicles
        List<Vehicle> vehicles = List.of(
            new Motorcycle("Yamaha R1", 300, 15),
            new Car("Tesla Model S", 250, 12),
            new Truck("Volvo FH16", 180, 8)
        );

        // Number of simulations
        int numberOfRaces = 5;
        double raceDistance = 1000; // Race distance in meters

        System.out.println("Starting race simulations...");
        Map<String, Integer> winCount = new HashMap<>();

        for (int i = 1; i <= numberOfRaces; i++) {
            System.out.println("\nRace " + i + " starts:");

            Vehicle winner = simulateRace(vehicles, raceDistance);
            System.out.println("Winner of race " + i + ": " + winner.getName());

            winCount.put(winner.getName(), winCount.getOrDefault(winner.getName(), 0) + 1);
        }

        System.out.println("\n--- Final Results ---");
        winCount.forEach((name, wins) -> System.out.println(name + " won " + wins + " race(s)."));

        String overallWinner = winCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("No Winner");

        System.out.println("\nOverall Winner: " + overallWinner);
    }

    private static Vehicle simulateRace(List<Vehicle> vehicles, double distance) {
        Map<Vehicle, Double> raceTimes = new HashMap<>();

        for (Vehicle vehicle : vehicles) {
            // Introduce some randomness to simulate real-world conditions
            double reliabilityFactor = ThreadLocalRandom.current().nextDouble(0.9, 1.1);
            double time = vehicle.raceTime(distance) * reliabilityFactor;
            raceTimes.put(vehicle, time);
            System.out.printf("%s finished in %.2f seconds.%n", vehicle.getName(), time);
        }

        return raceTimes.entrySet().stream()
            .min(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow();
    }
}
