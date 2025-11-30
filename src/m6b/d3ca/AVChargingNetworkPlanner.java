package m6b.d3ca;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AVChargingNetworkPlanner - Main Application
 *
 * This program demonstrates autonomous vehicle charging hub network
 * optimization using minimum spanning tree algorithms. It models a somewhat
 * realistic infrastructure problem: connecting regional AV charging hubs with
 * minimum total infrastructure cost. This is very similar to the gas station
 * problem.
 *
 * The system uses Prim's algorithm to find the optimal backbone network that
 * connects all charging hubs while minimizing development costs (land
 * acquisition, power infrastructure, communication lines, road preparation).
 *
 * Author: Cullen Kelley Course: AIT-512 Data Structures and Algorithms
 * Assignment: M6B-D3-CA Creative Assignment
 */
public class AVChargingNetworkPlanner {

    public static void main(String[] args) {
        // Display program header
        displayProgramHeader();

        // Create the charging network
        ChargingNetwork network = createChargingNetwork();

        // Display network structure
        network.displayNetwork();
        network.displayRoutes();

        // Compute MST using Prim's algorithm starting from hub 0
        MSTPrimAlgorithm mst = new MSTPrimAlgorithm(network);
        mst.computeMST(0);

        // Display MST results
        mst.displayMST();

        // Display summary analysis
        displaySummary(network, mst);

        // Create analyzer and display detailed analysis
        NetworkAnalyzer analyzer = new NetworkAnalyzer(network, mst);
        analyzer.displayAnalysisReport();
        analyzer.exportMSTTable();
    }

    /**
     * Displays the program header per requirements.
     *
     */
    private static void displayProgramHeader() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' HH:mm:ss"));

        System.out.println("================================================================");
        System.out.println("        AUTONOMOUS VEHICLE CHARGING NETWORK PLANNER            ");
        System.out.println("================================================================");
        System.out.println("Program: Optimal Charging Hub Infrastructure Backbone");
        System.out.println("Author: Cullen Kelley");
        System.out.println("Date: " + timestamp);
        System.out.println("Course: AIT-512 Data Structures and Algorithms");
        System.out.println("Assignment: M6B-D3-CA Creative Assignment");
        System.out.println("Algorithm: Prim's Minimum Spanning Tree");
        System.out.println("================================================================\n");
    }

    /**
     * Creates the charging network with 9 regional hubs and interconnecting
     * routes. Each route represents potential infrastructure connectivity with
     * associated costs.
     *
     * @return Configured ChargingNetwork
     */
    private static ChargingNetwork createChargingNetwork() {
        ChargingNetwork network = new ChargingNetwork();

        // Add 9 regional charging hubs for a reasonble approximation of possible conditions
        // Regional AV charging centers across multiple states
        network.addHub(new ChargingHub(0, "Dallas Hub", "North Texas", 32.7767, -96.7970));
        network.addHub(new ChargingHub(1, "Houston Hub", "Southeast Texas", 29.7604, -95.3698));
        network.addHub(new ChargingHub(2, "Austin Hub", "Central Texas", 30.2672, -97.7431));
        network.addHub(new ChargingHub(3, "San Antonio Hub", "South Texas", 29.4241, -98.4936));
        network.addHub(new ChargingHub(4, "Fort Worth Hub", "North Texas", 32.7555, -97.3308));
        network.addHub(new ChargingHub(5, "Corpus Christi Hub", "Coastal Texas", 27.5731, -97.1641));
        network.addHub(new ChargingHub(6, "Lubbock Hub", "West Texas", 33.5779, -101.8552));
        network.addHub(new ChargingHub(7, "Amarillo Hub", "Panhandle Texas", 35.0872, -101.6552));
        network.addHub(new ChargingHub(8, "El Paso Hub", "Far West Texas", 31.7614, -106.4883));

        System.out.println("=== BUILDING AUTONOMOUS VEHICLE CHARGING NETWORK ===");
        System.out.println("Creating infrastructure backbone for 9 regional charging hubs...\n");

        // Add routes with infrastructure costs (in thousands of dollars)
        // Costs represent: land acquisition, power infrastructure, communication, road prep
        network.addRoute(0, 1, 145.0);  // Dallas <-> Houston
        network.addRoute(0, 2, 95.0);   // Dallas <-> Austin
        network.addRoute(0, 4, 32.0);   // Dallas <-> Fort Worth
        network.addRoute(0, 6, 280.0);  // Dallas <-> Lubbock
        network.addRoute(1, 2, 165.0);  // Houston <-> Austin
        network.addRoute(1, 3, 210.0);  // Houston <-> San Antonio
        network.addRoute(1, 5, 215.0);  // Houston <-> Corpus Christi
        network.addRoute(2, 3, 140.0);  // Austin <-> San Antonio
        network.addRoute(2, 4, 105.0);  // Austin <-> Fort Worth
        network.addRoute(3, 5, 185.0);  // San Antonio <-> Corpus Christi
        network.addRoute(4, 6, 260.0);  // Fort Worth <-> Lubbock
        network.addRoute(6, 7, 185.0);  // Lubbock <-> Amarillo
        network.addRoute(6, 8, 520.0);  // Lubbock <-> El Paso
        network.addRoute(7, 8, 650.0);  // Amarillo <-> El Paso

        //Minor debug line
        System.out.println("Network construction completed!");

        return network;
    }

    /**
     * Displays summary analysis of the MST solution.
     *
     * @param network The charging network
     * @param mst The computed MST
     */
    private static void displaySummary(ChargingNetwork network, MSTPrimAlgorithm mst) {
        System.out.println("=== INFRASTRUCTURE OPTIMIZATION SUMMARY ===");
        System.out.println("\nNetwork Overview:");
        System.out.println("  Total Available Routes: " + network.getRouteCount());
        System.out.println("  Total Regional Hubs: " + network.getHubCount());

        System.out.println("\nMST Solution:");
        System.out.println("  Selected Infrastructure Routes: " + mst.getMSTEdgeCount());
        System.out.println("  Total Infrastructure Cost: $" + String.format("%.1f", mst.getTotalCost()) + "K");
        System.out.println("  Cost per Hub Connected: $" + String.format("%.1f", mst.getTotalCost() / network.getHubCount()) + "K");

        System.out.println("\nInfrastructure Efficiency:");
        System.out.println("  Routes Eliminated (Redundant): " + (network.getRouteCount() - mst.getMSTEdgeCount()));
        System.out.println("  Network Backbone: " + mst.getMSTEdgeCount() + " direct infrastructure links");
        System.out.println("  All " + network.getHubCount() + " hubs interconnected: YES");

        System.out.println("\nBusiness Impact:");
        System.out.println("  This minimum cost backbone connects all regional charging hubs,");
        System.out.println("  enabling autonomous vehicle coordination across the entire network");
        System.out.println("  while minimizing infrastructure investment.");

        System.out.println("\n============================================\n");
    }
}
