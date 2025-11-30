package m6b.d3ca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NetworkAnalyzer - Analysis and Reporting Utility
 *
 * Provides comprehensive analysis of charging network topology and MST
 * solutions. Supports formatted output, statistical analysis, and network
 * property validation.
 *
 * Author: Cullen Kelley Course: AIT-512 Data Structures and Algorithms
 * Assignment: M6B-D3-CA Creative Assignment
 */
public class NetworkAnalyzer {

    private ChargingNetwork network;
    private MSTPrimAlgorithm mst;

    /**
     * Constructs a NetworkAnalyzer with reference to a network and its MST.
     *
     * @param network The charging network to analyze
     * @param mst The computed MST
     */
    public NetworkAnalyzer(ChargingNetwork network, MSTPrimAlgorithm mst) {
        this.network = network;
        this.mst = mst;
    }

    /**
     * Displays network analysis report.
     *
     * @return void
     */
    public void displayAnalysisReport() {
        System.out.println("\n=== COMPREHENSIVE NETWORK ANALYSIS REPORT ===\n");

        displayNetworkTopology();
        System.out.println();
        displayMSTAnalysis();
        System.out.println();
        displayCostBreakdown();
        System.out.println();
        displayConnectivityMetrics();
    }

    /**
     * Displays network topology information.
     */
    private void displayNetworkTopology() {
        System.out.println("Network Topology:");
        System.out.println("-".repeat(50));
        System.out.println("Total Hubs: " + network.getHubCount());
        System.out.println("Total Routes: " + network.getRouteCount());

        // Calculate average degree
        int totalDegree = 0;
        for (ChargingHub hub : getHubList()) {
            int degree = network.getAdjacentHubs(hub.getId()).size();
            totalDegree += degree;
        }
        double avgDegree = (double) totalDegree / network.getHubCount();
        System.out.println("Average Hub Degree: " + String.format("%.2f", avgDegree));
        System.out.println("Network Density: " + String.format("%.2f%%",
                (100.0 * network.getRouteCount()) / (network.getHubCount() * (network.getHubCount() - 1) / 2)));
    }

    /**
     * Displays MST analysis.
     */
    private void displayMSTAnalysis() {
        System.out.println("Minimum Spanning Tree Analysis:");
        System.out.println("-".repeat(50));
        System.out.println("Total Routes Considered: " + network.getRouteCount());
        System.out.println("Routes Selected for MST: " + mst.getMSTEdgeCount());
        System.out.println("Routes Eliminated: " + (network.getRouteCount() - mst.getMSTEdgeCount()));
        System.out.println("Total Infrastructure Cost: $" + String.format("%.1f", mst.getTotalCost()) + "K");
        System.out.println("MST is Connected: " + (mst.getMSTEdgeCount() == network.getHubCount() - 1 ? "YES" : "NO"));
    }

    /**
     * Displays cost breakdown analysis.
     */
    private void displayCostBreakdown() {
        System.out.println("Cost Breakdown Analysis:");
        System.out.println("-".repeat(50));

        List<ChargingRoute> mstEdges = mst.getMSTEdges();
        Collections.sort(mstEdges, (r1, r2) -> Double.compare(r2.getCost(), r1.getCost()));

        System.out.println("Most Expensive Routes Selected:");
        int count = Math.min(5, mstEdges.size());
        for (int i = 0; i < count; i++) {
            ChargingRoute route = mstEdges.get(i);
            ChargingHub fromHub = network.getHub(route.getFromHub().getId());
            ChargingHub toHub = network.getHub(route.getToHub().getId());
            System.out.println("  " + (i + 1) + ". " + fromHub.getName() + " <-> "
                    + toHub.getName() + ": $" + String.format("%.1f", route.getCost()) + "K");
        }

        System.out.println("\nMost Affordable Routes Selected:");
        Collections.sort(mstEdges);
        count = Math.min(5, mstEdges.size());
        for (int i = 0; i < count; i++) {
            ChargingRoute route = mstEdges.get(i);
            ChargingHub fromHub = network.getHub(route.getFromHub().getId());
            ChargingHub toHub = network.getHub(route.getToHub().getId());
            System.out.println("  " + (i + 1) + ". " + fromHub.getName() + " <-> "
                    + toHub.getName() + ": $" + String.format("%.1f", route.getCost()) + "K");
        }
    }

    /**
     * Displays connectivity metrics.
     */
    private void displayConnectivityMetrics() {
        System.out.println("Connectivity Metrics:");
        System.out.println("-".repeat(50));

        List<ChargingHub> hubs = getHubList();
        System.out.println("Hub Connectivity in MST:");

        // Calculate degree in MST for each hub
        for (ChargingHub hub : hubs) {
            int degree = 0;
            for (ChargingRoute edge : mst.getMSTEdges()) {
                if (edge.getFromHub().getId() == hub.getId()
                        || edge.getToHub().getId() == hub.getId()) {
                    degree++;
                }
            }
            System.out.println("  " + hub.getName() + ": " + degree + " connection(s)");
        }

        System.out.println("\nAverage Infrastructure Connections per Hub: "
                + String.format("%.2f", (2.0 * mst.getMSTEdgeCount()) / network.getHubCount()));
    }

    /**
     * Gets list of all hubs in network (helper method).
     *
     * @return List of ChargingHub objects
     */
    private List<ChargingHub> getHubList() {
        List<ChargingHub> hubs = new ArrayList<>();
        for (int i = 0; i < network.getHubCount(); i++) {
            ChargingHub hub = network.getHub(i);
            if (hub != null) {
                hubs.add(hub);
            }
        }
        return hubs;
    }

    /**
     * Exports MST edges in tabular format to better visualize selected routes.
     * Essentially bringing this mess into a neat, pretty table. (AI was used
     * for this bit I'm not going to pretend I knew how to do this myself) This
     * also serves as a simple way to validate correctness of MST edges & as
     * final output.
     */
    public void exportMSTTable() {
        System.out.println("\n=== MST INFRASTRUCTURE ROUTES TABLE ===\n");
        System.out.println(String.format("%-25s | %-25s | %10s", "From Hub", "To Hub", "Cost ($K)"));
        System.out.println("-".repeat(65));

        for (ChargingRoute edge : mst.getMSTEdges()) {
            ChargingHub from = network.getHub(edge.getFromHub().getId());
            ChargingHub to = network.getHub(edge.getToHub().getId());
            System.out.println(String.format("%-25s | %-25s | %10.1f",
                    from.getName(), to.getName(), edge.getCost()));
        }

        System.out.println("-".repeat(65));
        System.out.println(String.format("%-25s | %-25s | %10.1f",
                "TOTAL", "", mst.getTotalCost()));
    }
}
