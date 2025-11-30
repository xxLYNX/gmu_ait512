package m6b.d3ca;

import ds.bag.Bag;
import ds.bag.FixedCapacityBag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChargingNetwork represents the autonomous vehicle charging hub network as an
 * undirected graph. Uses adjacency list representation with Bag ADT for storing
 * adjacent hubs.
 *
 * Author: Cullen Kelley Course: AIT-512 Data Structures and Algorithms
 * Assignment: M6B-D3-CA Creative Assignment
 */
public class ChargingNetwork {

    /**
     * Instance variables for the ChargingNetwork graph structure.
     */
    private Map<Integer, ChargingHub> hubs;
    private Map<Integer, Bag<Integer>> adjacencyList;
    private List<ChargingRoute> allRoutes;

    /**
     * Constructs an empty charging network.
     */
    public ChargingNetwork() {
        this.hubs = new HashMap<>();
        this.adjacencyList = new HashMap<>();
        this.allRoutes = new ArrayList<>();
    }

    /**
     * Adds a charging hub to the network.
     *
     * @param hub The hub to add
     */
    public void addHub(ChargingHub hub) {
        if (!hubs.containsKey(hub.getId())) {
            hubs.put(hub.getId(), hub);
            // Create adjacency list entry with Bag (capacity = number of hubs max)
            adjacencyList.put(hub.getId(), new FixedCapacityBag<>(hubs.size() + 5));
        }
    }

    /**
     * Adds a bidirectional route between two hubs. Since this is an undirected
     * graph, the edge exists in both directions.
     *
     * @param fromId Starting hub ID
     * @param toId Destination hub ID
     * @param cost Infrastructure cost for the route
     */
    public void addRoute(int fromId, int toId, double cost) {
        if (!hubs.containsKey(fromId) || !hubs.containsKey(toId)) {
            throw new IllegalArgumentException("One or both hubs do not exist");
        }

        // Add to adjacency lists (undirected - both directions)
        adjacencyList.get(fromId).add(toId);
        adjacencyList.get(toId).add(fromId);

        // Add to routes list
        ChargingRoute route = new ChargingRoute(hubs.get(fromId), hubs.get(toId), cost);
        allRoutes.add(route);
    }

    /**
     * Gets a hub by ID.
     *
     * @param id Hub identifier
     * @return The ChargingHub, or null if not found
     */
    public ChargingHub getHub(int id) {
        return hubs.get(id);
    }

    /**
     * Gets all hubs in the network.
     *
     * @return List of all hubs
     */
    public List<ChargingHub> getAllHubs() {
        return new ArrayList<>(hubs.values());
    }

    /**
     * Gets the number of hubs in the network.
     *
     * @return Count of hubs
     */
    public int getHubCount() {
        return hubs.size();
    }

    /**
     * Gets all adjacent hub IDs for a given hub. Uses the Bag ADT for storage.
     *
     * @param hubId Hub identifier
     * @return Bag containing adjacent hub IDs
     */
    public Bag<Integer> getAdjacentHubs(int hubId) {
        return adjacencyList.get(hubId);
    }

    /**
     * Gets all routes in the network.
     *
     * @return List of all routes
     */
    public List<ChargingRoute> getAllRoutes() {
        return new ArrayList<>(allRoutes);
    }

    /**
     * Gets the number of routes in the network.
     *
     * @return Count of routes (edges)
     */
    public int getRouteCount() {
        return allRoutes.size();
    }

    /**
     * Displays the complete network structure. Shows each hub and its
     * connections.
     */
    public void displayNetwork() {
        System.out.println("\n=== CHARGING NETWORK STRUCTURE ===");
        System.out.println("Total Hubs: " + getHubCount());
        System.out.println("Total Routes: " + getRouteCount());
        System.out.println("\nHub Connections:\n");

        for (int hubId : hubs.keySet()) {
            ChargingHub hub = hubs.get(hubId);
            System.out.println(hub.toString());
            System.out.print("  Connected to: ");

            Bag<Integer> adjacentIds = adjacencyList.get(hubId);
            boolean first = true;
            for (int adjacentId : adjacentIds) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(hubs.get(adjacentId).getName() + " (ID:" + adjacentId + ")");
                first = false;
            }
            System.out.println();
        }
        System.out.println("=====================================\n");
    }

    /**
     * Displays all routes with costs.
     */
    public void displayRoutes() {
        System.out.println("\n=== ALL CHARGING ROUTES ===");
        for (ChargingRoute route : allRoutes) {
            System.out.println(route.toString() + " | Cost: $" + String.format("%.1f", route.getCost()) + "K");
        }
        System.out.println("============================\n");
    }
}
