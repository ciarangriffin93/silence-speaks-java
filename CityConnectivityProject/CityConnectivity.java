package CityConnectivityProject;

import java.io.*;
import java.util.*;

public class CityConnectivity {

    private Map<String, List<String>> cityGraph;

    public CityConnectivity() {
        cityGraph = new HashMap<>();
    }

    public void buildGraphFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] cities = line.split(",");
            String city1 = cities[0].trim();
            String city2 = cities[1].trim();

            cityGraph.putIfAbsent(city1, new ArrayList<>());
            cityGraph.putIfAbsent(city2, new ArrayList<>());
            cityGraph.get(city1).add(city2);
            cityGraph.get(city2).add(city1); // Undirected graph
        }
        reader.close();
    }

    public boolean areCitiesConnected(String city1, String city2) {
        if (!cityGraph.containsKey(city1) || !cityGraph.containsKey(city2)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        return dfs(city1, city2, visited);
    }

    private boolean dfs(String currentCity, String targetCity, Set<String> visited) {
        if (currentCity.equals(targetCity)) {
            return true;
        }

        visited.add(currentCity);

        for (String neighbor : cityGraph.get(currentCity)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, targetCity, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java CityConnectivity <fileName> <city1> <city2>");
            return;
        }

        String fileName = args[0];
        String city1 = args[1];
        String city2 = args[2];

        CityConnectivity cityConnectivity = new CityConnectivity();

        try {
            cityConnectivity.buildGraphFromFile(fileName);

            if (cityConnectivity.areCitiesConnected(city1, city2)) {
                System.out.println(city1 + " and " + city2 + " are connected.");
            } else {
                System.out.println(city1 + " and " + city2 + " are NOT connected.");
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}