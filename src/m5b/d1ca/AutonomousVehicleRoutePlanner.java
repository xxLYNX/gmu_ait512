package m5b.d1ca;

import ds.bag.Bag;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autonomous Vehicle Route Planner - Main Application
 *
 * This program demonstrates a real-world gas station route planning system for
 * autonomous vehicles using graph data structures and Breadth-First Search
 * (BFS). The system models gas stations as vertices in an undirected graph with
 * direct routes as edges, using the standard Bag implementation for adjacency
 * lists.
 *
 * Author: Cullen Juriskell Course: AIT-512 Data Structures and Algorithms
 * Assignment: M5B-D1-52 Creative Assignment
 */
public class AutonomousVehicleRoutePlanner {

    public static void main(String[] args) {
        // Display required program header
        displayProgramHeader();

        // Create and populate the gas station network
        GasStationNetwork network = createRegionalGasStationNetwork();

        // Display the network structure
        network.displayNetwork();

        // Initialize the route management system
        RouteManager routeManager = new RouteManager(network);

        // Demonstrate autonomous vehicle route planning capabilities
        demonstrateRouteOptimization(network, routeManager);

        // Analyze network connectivity for autonomous vehicle safety
        demonstrateConnectivityAnalysis(routeManager);

        // Show emergency fuel planning scenarios
        demonstrateEmergencyFuelPlanning(network, routeManager);

        // Fleet management recommendations
        demonstrateFleetManagement(routeManager);

        // Display execution summary
        displayExecutionSummary(network);
    }

    /**
     * Displays the required program header with program name, student name, and
     * date.
     */
    private static void displayProgramHeader() {
        System.out.println("================================================================");
        System.out.println("         AUTONOMOUS VEHICLE GAS STATION ROUTE PLANNER          ");
        System.out.println("================================================================");
        System.out.println("Program: Gas Station Network for Autonomous Vehicles");
        System.out.println("Name: Cullen Kelley");
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' HH:mm:ss")));
        System.out.println("================================================================");

        System.out.println("\nOVERVIEW:");
        System.out.println("This system models a regional gas station network as an undirected");
        System.out.println("graph to support autonomous vehicle route planning. Gas stations are");
        System.out.println("vertices connected by direct driving routes (edges). The system uses");
        System.out.println("Breadth-First Search algorithms to find optimal refueling paths,");
        System.out.println("analyze network connectivity, and provide emergency fuel planning");
        System.out.println("for safe autonomous vehicle operation.");

        System.out.println("\nKEY FEATURES:");
        System.out.println("  1. BFS-based shortest path route planning");
        System.out.println("  2. Network connectivity analysis for safety");
        System.out.println("  3. Emergency fuel station location services");
        System.out.println("  4. Fleet distribution optimization");
        System.out.println("  5. Integration with standard Bag ADT implementation");
        System.out.println();
    }

    /**
     * Creates a comprehensive gas station network for demonstration. Models a
     * realistic regional highway network with major gas station brands.
     *
     * @return Populated GasStationNetwork for autonomous vehicle testing
     */
    private static GasStationNetwork createRegionalGasStationNetwork() {
        System.out.println("=== BUILDING REGIONAL GAS STATION NETWORK ===");
        System.out.println("Creating highway network infrastructure for autonomous vehicles...\n");

        GasStationNetwork network = new GasStationNetwork();

        // Create gas stations along major highway corridors
        System.out.println("STEP 1: Adding Gas Stations to Network");
        System.out.println("--------------------------------------");

        // Highway 95 North-South Corridor
        System.out.println("I-95 North-South Corridor:");
        GasStation shell1 = network.addGasStation("Shell", "I-95 Exit 42 North", 38.9072, -77.0369);
        GasStation bp1 = network.addGasStation("BP", "I-95 Exit 45 North", 38.9472, -77.0869);
        GasStation exxon1 = network.addGasStation("Exxon", "I-95 Exit 48 North", 38.9872, -77.1369);
        System.out.println("  - Shell (ID: " + shell1.getStationId() + ") at I-95 Exit 42");
        System.out.println("  - BP (ID: " + bp1.getStationId() + ") at I-95 Exit 45");
        System.out.println("  - Exxon (ID: " + exxon1.getStationId() + ") at I-95 Exit 48");

        // Highway 66 East-West Corridor  
        System.out.println("\nI-66 East-West Corridor:");
        GasStation chevron1 = network.addGasStation("Chevron", "I-66 Exit 57 West", 38.8572, -77.2869);
        GasStation mobil1 = network.addGasStation("Mobil", "I-66 Exit 60 West", 38.8572, -77.3369);
        System.out.println("  - Chevron (ID: " + chevron1.getStationId() + ") at I-66 Exit 57");
        System.out.println("  - Mobil (ID: " + mobil1.getStationId() + ") at I-66 Exit 60");

        // Local Route 7 Network
        System.out.println("\nRoute 7 Local Network:");
        GasStation sunoco1 = network.addGasStation("Sunoco", "Route 7 Tysons", 38.9172, -77.2269);
        GasStation wawa1 = network.addGasStation("Wawa", "Route 7 Reston", 38.9572, -77.3569);
        System.out.println("  - Sunoco (ID: " + sunoco1.getStationId() + ") at Route 7 Tysons");
        System.out.println("  - Wawa (ID: " + wawa1.getStationId() + ") at Route 7 Reston");

        // Additional strategic locations
        System.out.println("\nStrategic Locations:");
        GasStation shell2 = network.addGasStation("Shell", "Dulles Airport Area", 38.9444, -77.4558);
        GasStation bp2 = network.addGasStation("BP", "Fairfax City Center", 38.8462, -77.3063);
        GasStation exxon2 = network.addGasStation("Exxon", "Manassas Junction", 38.7509, -77.4753);
        System.out.println("  - Shell (ID: " + shell2.getStationId() + ") at Dulles Airport");
        System.out.println("  - BP (ID: " + bp2.getStationId() + ") at Fairfax City Center");
        System.out.println("  - Exxon (ID: " + exxon2.getStationId() + ") at Manassas Junction");

        System.out.println("\nNetwork Status: Added " + network.getStationCount() + " gas stations successfully");

        // Add fuel types and amenities using Bag implementation
        System.out.println("\nSTEP 2: Configuring Station Amenities");
        System.out.println("-------------------------------------");
        configureFuelAndAmenities(shell1, true, true);
        configureFuelAndAmenities(bp1, true, false);
        configureFuelAndAmenities(exxon1, false, true);
        configureFuelAndAmenities(chevron1, true, true);
        configureFuelAndAmenities(mobil1, false, false);
        configureFuelAndAmenities(sunoco1, true, false);
        configureFuelAndAmenities(wawa1, true, true);
        configureFuelAndAmenities(shell2, true, true);
        configureFuelAndAmenities(bp2, false, true);
        configureFuelAndAmenities(exxon2, true, false);

        // Create highway route connections (undirected graph)
        System.out.println("\nSTEP 3: Establishing Route Connections");
        System.out.println("--------------------------------------");

        // I-95 North-South Highway
        System.out.println("Highway Corridors:");
        network.addRoute(shell1.getStationId(), bp1.getStationId());
        network.addRoute(bp1.getStationId(), exxon1.getStationId());
        System.out.println("  - I-95 North-South corridor connected");

        // I-66 East-West Highway  
        network.addRoute(chevron1.getStationId(), mobil1.getStationId());
        System.out.println("  - I-66 East-West corridor connected");

        // Route 7 Local Network
        network.addRoute(sunoco1.getStationId(), wawa1.getStationId());
        System.out.println("  - Route 7 local network connected");

        // Inter-highway connections (major interchanges)
        System.out.println("\nMajor Interchanges:");
        network.addRoute(shell1.getStationId(), chevron1.getStationId());  // I-95/I-66 interchange
        network.addRoute(bp1.getStationId(), sunoco1.getStationId());       // Highway to local route
        network.addRoute(chevron1.getStationId(), shell2.getStationId());   // Access to airport
        network.addRoute(sunoco1.getStationId(), bp2.getStationId());       // Local network expansion
        network.addRoute(mobil1.getStationId(), exxon2.getStationId());     // Western expansion
        network.addRoute(wawa1.getStationId(), shell2.getStationId());      // Northern connection
        System.out.println("  - I-95/I-66 interchange connected (Shell to Chevron)");
        System.out.println("  - Highway-to-local routes established");
        System.out.println("  - Airport access routes connected");
        System.out.println("  - Regional connectivity enhanced");

        System.out.println("\nNetwork construction completed successfully!");
        System.out.println("Ready for autonomous vehicle route planning.\n");

        return network;
    }

    /**
     * Helper method to configure fuel types and amenities for gas stations.
     * Uses the Bag implementation to store station features.
     *
     * @param station Gas station to configure
     * @param hasElectric Whether station has electric charging
     * @param hasFullService Whether station provides full amenities
     */
    private static void configureFuelAndAmenities(GasStation station, boolean hasElectric, boolean hasFullService) {
        // Add standard fuel types using Bag
        station.addFuelType("Regular");
        station.addFuelType("Premium");
        station.addFuelType("Diesel");

        if (hasElectric) {
            station.addFuelType("Electric");
            station.addAmenity("EV Charging Station");
        }

        // Add basic amenities using Bag
        station.addAmenity("Convenience Store");
        station.addAmenity("Restrooms");
        station.addAmenity("ATM");

        if (hasFullService) {
            station.addAmenity("Car Wash");
            station.addAmenity("Auto Repair");
            station.addAmenity("Food Service");
        }

        System.out.println("Station Configuration:");
        System.out.println("  " + station.getStationName() + " (ID: " + station.getStationId() + ")");
        System.out.println("    Fuel Types: " + (hasElectric ? "Regular, Premium, Diesel, Electric" : "Regular, Premium, Diesel"));
        System.out.println("    Amenities: " + (hasFullService ? "Full Service (Car Wash, Auto Repair, Food)" : "Basic Service"));
        System.out.println("    Electric Charging: " + (hasElectric ? "Available" : "Not Available"));
    }

    /**
     * Demonstrates optimal route planning for autonomous vehicles. Shows
     * BFS-based shortest path calculation between various stations.
     */
    private static void demonstrateRouteOptimization(GasStationNetwork network, RouteManager routeManager) {
        System.out.println("=== AUTONOMOUS VEHICLE ROUTE OPTIMIZATION DEMO ===");
        System.out.println("Demonstrating BFS-based optimal pathfinding for autonomous vehicles\n");

        // Scenario 1: Cross-state autonomous vehicle route
        System.out.println("SCENARIO 1: Long-Distance Route Planning");
        System.out.println("=======================================");
        System.out.println("Objective: Plan route for autonomous electric vehicle");
        System.out.println("From: Station 1 (Shell at I-95 Exit 42)");
        System.out.println("To: Station 10 (Exxon at Manassas Junction)");
        System.out.println("Required Fuel: Electric charging capability");
        RouteManager.RouteRecommendation route1 = routeManager.findOptimalRoute(1, 10, "Electric");
        routeManager.displayRouteDetails(route1);
        System.out.println();

        // Scenario 2: Local area navigation
        System.out.println("SCENARIO 2: Local Area Navigation");
        System.out.println("=================================");
        System.out.println("Objective: Short-distance autonomous navigation");
        System.out.println("From: Station 6 (Sunoco at Route 7 Tysons)");
        System.out.println("To: Station 8 (Shell at Dulles Airport)");
        System.out.println("Required Fuel: Premium gasoline");
        RouteManager.RouteRecommendation route2 = routeManager.findOptimalRoute(6, 8, "Premium");
        routeManager.displayRouteDetails(route2);
        System.out.println();

        // Scenario 3: Emergency rerouting
        System.out.println("SCENARIO 3: Emergency Rerouting");
        System.out.println("===============================");
        System.out.println("Objective: Emergency fuel planning for delivery truck");
        System.out.println("From: Station 3 (Exxon at I-95 Exit 48)");
        System.out.println("To: Station 9 (BP at Fairfax City Center)");
        System.out.println("Required Fuel: Diesel for commercial vehicle");
        RouteManager.RouteRecommendation route3 = routeManager.findOptimalRoute(3, 9, "Diesel");
        routeManager.displayRouteDetails(route3);
    }

    /**
     * Demonstrates network connectivity analysis for autonomous vehicle safety.
     * Uses BFS to verify that all stations are reachable for reliable
     * operation.
     */
    private static void demonstrateConnectivityAnalysis(RouteManager routeManager) {
        System.out.println("\n=== NETWORK CONNECTIVITY ANALYSIS FOR SAFETY ===");
        System.out.println("Analyzing network reliability for autonomous vehicle operations\n");

        boolean isConnected = routeManager.analyzeNetworkConnectivity();

        System.out.println("\nSAFETY ASSESSMENT RESULTS:");
        System.out.println("=========================");
        if (isConnected) {
            System.out.println("Status: NETWORK FULLY CONNECTED");
            System.out.println("Safety Level: HIGH");
            System.out.println("Assessment:");
            System.out.println("  - All gas stations are reachable from any starting point");
            System.out.println("  - Autonomous vehicles can safely navigate between any two locations");
            System.out.println("  - No isolated areas or unreachable stations detected");
            System.out.println("  - Network is suitable for reliable autonomous vehicle operation");
        } else {
            System.out.println("Status: NETWORK HAS ISOLATED COMPONENTS");
            System.out.println("Safety Level: MEDIUM - CAUTION REQUIRED");
            System.out.println("Assessment:");
            System.out.println("  - Some stations are unreachable from certain starting points");
            System.out.println("  - Autonomous vehicles may face routing failures in isolated areas");
            System.out.println("  - Additional route connections recommended");
            System.out.println("  - Manual intervention may be required in disconnected regions");
        }
        System.out.println();
    }

    /**
     * Demonstrates emergency fuel planning scenarios for autonomous vehicles.
     * Shows how BFS finds nearby stations with required fuel types.
     */
    private static void demonstrateEmergencyFuelPlanning(GasStationNetwork network, RouteManager routeManager) {
        System.out.println("=== EMERGENCY FUEL PLANNING SCENARIOS ===");
        System.out.println("Testing emergency fuel location capabilities using BFS search\n");

        // Emergency Scenario 1: Electric vehicle needs charging
        System.out.println("EMERGENCY SCENARIO 1: Electric Vehicle Low Battery");
        System.out.println("================================================");
        System.out.println("Vehicle Type: Autonomous Electric Vehicle");
        System.out.println("Current Location: Station 2 (BP at I-95 Exit 45)");
        System.out.println("Search Radius: 3 station hops");
        System.out.println("Required Service: Electric charging capability");
        Bag<Integer> electricStations = routeManager.findNearbyStationsWithFuel(2, 3, "Electric");
        displayEmergencyResults(network, electricStations, "Electric");

        // Emergency Scenario 2: Diesel truck needs refueling
        System.out.println("EMERGENCY SCENARIO 2: Commercial Delivery Truck");
        System.out.println("==============================================");
        System.out.println("Vehicle Type: Autonomous Delivery Truck");
        System.out.println("Current Location: Station 5 (Mobil at I-66 Exit 60)");
        System.out.println("Search Radius: 2 station hops");
        System.out.println("Required Service: Diesel fuel");
        Bag<Integer> dieselStations = routeManager.findNearbyStationsWithFuel(5, 2, "Diesel");
        displayEmergencyResults(network, dieselStations, "Diesel");

        // Emergency Scenario 3: Standard vehicle needs premium fuel
        System.out.println("EMERGENCY SCENARIO 3: High-Performance Vehicle");
        System.out.println("=============================================");
        System.out.println("Vehicle Type: Autonomous Sports Car");
        System.out.println("Current Location: Station 7 (Wawa at Route 7 Reston)");
        System.out.println("Search Radius: 4 station hops");
        System.out.println("Required Service: Premium gasoline");
        Bag<Integer> premiumStations = routeManager.findNearbyStationsWithFuel(7, 4, "Premium");
        displayEmergencyResults(network, premiumStations, "Premium");
    }

    /**
     * Helper method to display emergency fuel search results.
     *
     * @param network Gas station network
     * @param stationIds Bag of station IDs with required fuel
     * @param fuelType Required fuel type
     */
    private static void displayEmergencyResults(GasStationNetwork network, Bag<Integer> stationIds, String fuelType) {
        System.out.println("\nEMERGENCY SEARCH RESULTS:");
        System.out.println("------------------------");
        if (stationIds.isEmpty()) {
            System.out.println("Status: NO SUITABLE STATIONS FOUND");
            System.out.println("Alert: WARNING - No stations found with " + fuelType + " within search radius");
            System.out.println("Recommendations:");
            System.out.println("  1. Expand search radius to include more distant stations");
            System.out.println("  2. Consider alternative fuel type if vehicle supports it");
            System.out.println("  3. Request emergency roadside assistance");
        } else {
            System.out.println("Status: SUITABLE STATIONS LOCATED");
            System.out.println("Found " + stationIds.size() + " station(s) with " + fuelType + " fuel:");
            System.out.println("Recommended Stations:");
            int count = 1;
            for (Integer stationId : stationIds) {
                GasStation station = network.getGasStation(stationId);
                System.out.println("  " + count + ". " + station.getCompactInfo());
                count++;
            }
        }
        System.out.println();
    }

    /**
     * Demonstrates fleet management capabilities for autonomous vehicle
     * operators. Shows optimal distribution strategies using BFS coverage
     * analysis.
     */
    private static void demonstrateFleetManagement(RouteManager routeManager) {
        System.out.println("=== AUTONOMOUS VEHICLE FLEET MANAGEMENT ===");
        System.out.println("Optimizing vehicle distribution for maximum regional coverage\n");

        System.out.println("FLEET SCENARIO: Ride-Sharing Service Deployment");
        System.out.println("==============================================");
        System.out.println("Fleet Size: 5 autonomous vehicles");
        System.out.println("Objective: Maximize coverage area and fuel access");
        System.out.println("Strategy: Position vehicles at strategic gas station locations\n");

        // Scenario: Ride-sharing fleet of 5 autonomous vehicles
        routeManager.generateFleetDistributionRecommendations(5);

        System.out.println("FLEET MANAGEMENT INSIGHTS:");
        System.out.println("==========================");
        System.out.println("Strategic Benefits:");
        System.out.println("  1. Vehicles positioned for maximum regional coverage");
        System.out.println("  2. Each vehicle can reach multiple fuel options within 2 hops");
        System.out.println("  3. Distribution minimizes risk of fuel shortages");
        System.out.println("  4. Strategic positioning enables efficient passenger pickup");
        System.out.println("  5. Redundant coverage ensures service reliability");
        System.out.println();
    }

    /**
     * Displays comprehensive execution summary and program statistics.
     *
     * @param network The gas station network used in demonstrations
     */
    private static void displayExecutionSummary(GasStationNetwork network) {

        System.out.println("\nNETWORK STATISTICS:");
        System.out.println("==================");
        System.out.println("  Total Gas Stations: " + network.getStationCount());
        System.out.println("  Total Direct Routes: " + network.getRouteCount());
        System.out.println("  Graph Type: Undirected, Unweighted");
        System.out.println("  Representation: Adjacency List with Bag ADT");

        System.out.println("\nALGORITHM PERFORMANCE:");
        System.out.println("=====================");
        System.out.println("  Primary Algorithm: Breadth-First Search (BFS)");
        System.out.println("  Time Complexity: O(V + E) where V=stations, E=routes");
        System.out.println("  Space Complexity: O(V) for BFS queue and visited tracking");
        System.out.println("  Data Structure Integration: Standard Bag ADT for adjacency lists");

    }
}
