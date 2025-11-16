package m5b.d1ca;

import ds.bag.Bag;
import ds.bag.FixedCapacityBag;
import java.util.*;

/**
 * Route Management System for Autonomous Vehicles. This class provides
 * intelligent route planning and analysis services for autonomous vehicles
 * navigating a gas station network. It uses Breadth-First Search algorithms to
 * find optimal refueling routes, analyze network connectivity, and provide
 * emergency fuel planning.
 */
public class RouteManager {

    private GasStationNetwork network;

    /**
     * Constructs a new Route Manager for autonomous vehicle planning.
     *
     * @param network The gas station network to manage routes for
     */
    public RouteManager(GasStationNetwork network) {
        this.network = network;
    }

    /**
     * Represents a route recommendation for autonomous vehicles. Contains path
     * information, distance metrics, and fuel planning data.
     */
    public static class RouteRecommendation {

        private List<Integer> stationPath;
        private int totalHops;
        private double estimatedDistance;
        private Bag<String> requiredFuelTypes;
        private String routeDescription;

        public RouteRecommendation(List<Integer> stationPath, double estimatedDistance) {
            this.stationPath = stationPath;
            this.totalHops = stationPath.size() - 1;
            this.estimatedDistance = estimatedDistance;
            this.requiredFuelTypes = new FixedCapacityBag<>(5);
        }

        // Getter methods
        public List<Integer> getStationPath() {
            return stationPath;
        }

        public int getTotalHops() {
            return totalHops;
        }

        public double getEstimatedDistance() {
            return estimatedDistance;
        }

        public Bag<String> getRequiredFuelTypes() {
            return requiredFuelTypes;
        }

        public String getRouteDescription() {
            return routeDescription;
        }

        public void addRequiredFuelType(String fuelType) {
            requiredFuelTypes.add(fuelType);
        }

        public void setRouteDescription(String description) {
            this.routeDescription = description;
        }
    }

    /**
     * Finds the optimal route between two gas stations for autonomous vehicles.
     * Uses BFS to ensure the shortest path (minimum stops) for efficient fuel
     * planning.
     *
     * @param startStationId Starting gas station ID
     * @param destinationStationId Target gas station ID
     * @param preferredFuelType Preferred fuel type for the autonomous vehicle
     * @return RouteRecommendation with optimal path details, or null if no
     * route exists
     */
    public RouteRecommendation findOptimalRoute(int startStationId, int destinationStationId,
            String preferredFuelType) {
        System.out.println("\n=== AUTONOMOUS VEHICLE ROUTE PLANNING ===");

        GasStation startStation = network.getGasStation(startStationId);
        GasStation destinationStation = network.getGasStation(destinationStationId);

        if (startStation == null || destinationStation == null) {
            System.out.println("Error: Invalid station IDs provided");
            return null;
        }

        System.out.println("Origin: " + startStation.getCompactInfo());
        System.out.println("Destination: " + destinationStation.getCompactInfo());
        System.out.println("Preferred Fuel: " + preferredFuelType);
        System.out.println("\nCalculating optimal route using BFS...");

        // Use BFS to find shortest path
        List<Integer> shortestPath = network.findShortestRoute(startStationId, destinationStationId);

        if (shortestPath == null || shortestPath.isEmpty()) {
            System.out.println("No route found between specified stations");
            return null;
        }

        // Calculate route metrics
        double totalDistance = calculateRouteDistance(shortestPath);
        RouteRecommendation recommendation = new RouteRecommendation(shortestPath, totalDistance);

        // Add fuel type requirement
        recommendation.addRequiredFuelType(preferredFuelType);

        // Generate route description
        String description = generateRouteDescription(shortestPath, preferredFuelType);
        recommendation.setRouteDescription(description);

        System.out.println("Route planning completed successfully!");
        return recommendation;
    }

    /**
     * Finds the nearest gas stations within a specified radius using BFS.
     * Critical for autonomous vehicles running low on fuel.
     *
     * @param currentStationId Current location station ID
     * @param maxHops Maximum number of station hops to search
     * @param requiredFuelType Required fuel type
     * @return Bag of nearby station IDs that have the required fuel
     */
    public Bag<Integer> findNearbyStationsWithFuel(int currentStationId, int maxHops,
            String requiredFuelType) {
        System.out.println("\n=== EMERGENCY FUEL SEARCH ===");
        System.out.println("Current Location: " + network.getGasStation(currentStationId).getCompactInfo());
        System.out.println("Required Fuel Type: " + requiredFuelType);
        System.out.println("Search Radius: " + maxHops + " hops");

        Bag<Integer> nearbyStations = new FixedCapacityBag<>(50);

        // Use BFS to explore nearby stations
        Map<Integer, Integer> reachableStations = network.breadthFirstSearch(currentStationId, maxHops);

        System.out.println("\nAnalyzing fuel availability:");
        for (Map.Entry<Integer, Integer> entry : reachableStations.entrySet()) {
            int stationId = entry.getKey();
            int distance = entry.getValue();

            // Skip the current station
            if (stationId == currentStationId) {
                continue;
            }

            GasStation station = network.getGasStation(stationId);
            if (station.hasFuelType(requiredFuelType)) {
                nearbyStations.add(stationId);
                System.out.println("  " + station.getCompactInfo()
                        + " (Distance: " + distance + " hops) - Has " + requiredFuelType);
            } else {
                System.out.println("  " + station.getCompactInfo()
                        + " (Distance: " + distance + " hops) - No " + requiredFuelType);
            }
        }

        System.out.println("Found " + nearbyStations.size() + " stations with required fuel");
        return nearbyStations;
    }

    /**
     * Analyzes network connectivity to ensure robust autonomous vehicle
     * operation. Uses BFS to verify that all stations are reachable from any
     * starting point.
     *
     * @return true if network is fully connected, false if there are isolated
     * components
     */
    public boolean analyzeNetworkConnectivity() {
        System.out.println("\n=== NETWORK CONNECTIVITY ANALYSIS ===");

        Collection<GasStation> allStations = network.getAllGasStations();
        int totalStations = allStations.size();

        if (totalStations == 0) {
            System.out.println("Network is empty - no stations to analyze");
            return true;
        }

        // Pick the first station as starting point for BFS
        GasStation startStation = allStations.iterator().next();
        Map<Integer, Integer> reachableStations = network.breadthFirstSearch(
                startStation.getStationId(), Integer.MAX_VALUE);

        int reachableCount = reachableStations.size();
        double connectivityPercentage = (double) reachableCount / totalStations * 100;

        System.out.println("Network Analysis Results:");
        System.out.println("  Total Stations: " + totalStations);
        System.out.println("  Reachable from " + startStation.getStationName() + ": " + reachableCount);
        System.out.println("  Connectivity: " + String.format("%.1f", connectivityPercentage) + "%");

        boolean isConnected = (connectivityPercentage == 100.0);

        if (isConnected) {
            System.out.println("  Status: FULLY CONNECTED");
            System.out.println("  All stations are reachable - safe for autonomous vehicle operation");
        } else {
            System.out.println("  Status: DISCONNECTED NETWORK");
            System.out.println("  Warning: Some stations are isolated - may cause routing failures");

            // Identify isolated stations
            Set<Integer> reachableIds = reachableStations.keySet();
            System.out.println("  Isolated stations:");
            for (GasStation station : allStations) {
                if (!reachableIds.contains(station.getStationId())) {
                    System.out.println("    - " + station.getCompactInfo());
                }
            }
        }

        System.out.println("==========================================");
        return isConnected;
    }

    /**
     * Provides route planning recommendations for autonomous vehicle fleet
     * management. Uses BFS to analyze optimal station distribution and
     * coverage.
     *
     * @param fleetSize Number of autonomous vehicles to plan for
     */
    public void generateFleetDistributionRecommendations(int fleetSize) {
        System.out.println("\n=== AUTONOMOUS VEHICLE FLEET DISTRIBUTION ===");
        System.out.println("Planning for fleet size: " + fleetSize + " vehicles");

        Collection<GasStation> allStations = network.getAllGasStations();
        List<GasStation> stationList = new ArrayList<>(allStations);

        if (stationList.size() < fleetSize) {
            System.out.println("Warning: More vehicles than stations - some vehicles will share locations");
        }

        System.out.println("\nRecommended vehicle distribution:");

        // Simple distribution strategy - place vehicles at evenly spaced stations
        for (int i = 0; i < Math.min(fleetSize, stationList.size()); i++) {
            int stationIndex = (i * stationList.size()) / fleetSize;
            GasStation station = stationList.get(stationIndex);

            System.out.println("Vehicle " + (i + 1) + ": " + station.getCompactInfo());

            // Show coverage from this station using BFS
            Map<Integer, Integer> coverage = network.breadthFirstSearch(station.getStationId(), 2);
            System.out.println("  Coverage: Can reach " + coverage.size()
                    + " stations within 2 hops");
        }

        System.out.println("===============================================");
    }

    /**
     * Calculates the approximate total distance for a route path.
     *
     * @param stationPath List of station IDs representing the route
     * @return Estimated total distance in kilometers
     */
    private double calculateRouteDistance(List<Integer> stationPath) {
        double totalDistance = 0.0;

        for (int i = 0; i < stationPath.size() - 1; i++) {
            GasStation current = network.getGasStation(stationPath.get(i));
            GasStation next = network.getGasStation(stationPath.get(i + 1));
            totalDistance += current.getDistanceTo(next);
        }

        return totalDistance;
    }

    /**
     * Generates a human-readable description of the route.
     *
     * @param stationPath List of station IDs in the route
     * @param fuelType Required fuel type
     * @return Formatted route description
     */
    private String generateRouteDescription(List<Integer> stationPath, String fuelType) {
        StringBuilder description = new StringBuilder();
        description.append("Autonomous Vehicle Route Plan:\n");

        for (int i = 0; i < stationPath.size(); i++) {
            GasStation station = network.getGasStation(stationPath.get(i));

            if (i == 0) {
                description.append("START: ");
            } else if (i == stationPath.size() - 1) {
                description.append("DESTINATION: ");
            } else {
                description.append("WAYPOINT " + i + ": ");
            }

            description.append(station.getCompactInfo());

            if (station.hasFuelType(fuelType)) {
                description.append(" [" + fuelType + " Available]");
            } else {
                description.append(" [No " + fuelType + "]");
            }

            description.append("\n");
        }

        return description.toString();
    }

    /**
     * Displays detailed route information for autonomous vehicle navigation.
     *
     * @param recommendation Route recommendation to display
     */
    public void displayRouteDetails(RouteRecommendation recommendation) {
        if (recommendation == null) {
            System.out.println("No route recommendation available");
            return;
        }

        System.out.println("\n=== ROUTE DETAILS FOR AUTONOMOUS VEHICLE ===");
        System.out.println("Total Hops: " + recommendation.getTotalHops());
        System.out.println("Estimated Distance: "
                + String.format("%.2f", recommendation.getEstimatedDistance()) + " km");

        System.out.print("Required Fuel Types: ");
        for (String fuelType : recommendation.getRequiredFuelTypes()) {
            System.out.print(fuelType + " ");
        }
        System.out.println("\n");

        System.out.println(recommendation.getRouteDescription());
        System.out.println("===============================================");
    }
}
