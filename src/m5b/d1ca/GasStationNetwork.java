package m5b.d1ca;

import ds.bag.Bag;
import ds.bag.FixedCapacityBag;
import java.util.*;

/**
 * Represents a gas station network as an undirected graph for autonomous
 * vehicle route planning. Gas stations are vertices and direct routes are
 * edges. Uses the standard Bag implementation from the course module for
 * adjacency lists. This ensures compatibility with the existing Bag ADT while
 * providing efficient graph operations for route planning algorithms.
 */
public class GasStationNetwork {

    private Map<Integer, GasStation> stations;      // Maps station ID to GasStation object
    private Map<Integer, Bag<Integer>> adjacencyList; // Uses standard Bag for neighbors
    private int nextStationId;                       // Auto-incrementing station ID

    /**
     * Constructs an empty gas station network. Initializes the graph structure
     * using HashMap for stations and Bag implementation for adjacency
     * relationships.
     */
    public GasStationNetwork() {
        stations = new HashMap<>();
        adjacencyList = new HashMap<>();
        nextStationId = 1;
    }

    /**
     * Adds a new gas station to the network.
     *
     * @param stationName Brand name of the gas station
     * @param location Human-readable location description
     * @param latitude GPS latitude coordinate
     * @param longitude GPS longitude coordinate
     * @return The newly created GasStation object
     */
    public GasStation addGasStation(String stationName, String location,
            double latitude, double longitude) {
        GasStation newStation = new GasStation(nextStationId, stationName, location,
                latitude, longitude);
        stations.put(nextStationId, newStation);

        // Initialize adjacency bag with reasonable capacity for gas station connections
        adjacencyList.put(nextStationId, new FixedCapacityBag<>(50));

        nextStationId++;
        return newStation;
    }

    /**
     * Creates a direct route (undirected edge) between two gas stations. This
     * represents a feasible driving connection for autonomous vehicles.
     *
     * @param stationId1 ID of the first gas station
     * @param stationId2 ID of the second gas station
     * @return true if route was created successfully, false otherwise
     */
    public boolean addRoute(int stationId1, int stationId2) {
        // Validate that both stations exist
        if (!stations.containsKey(stationId1) || !stations.containsKey(stationId2)) {
            System.err.println("Error: One or both stations do not exist");
            return false;
        }

        // Prevent self-loops
        if (stationId1 == stationId2) {
            System.err.println("Error: Cannot create route from station to itself");
            return false;
        }

        // Check if route already exists
        if (areDirectlyConnected(stationId1, stationId2)) {
            System.out.println("Route already exists between stations "
                    + stationId1 + " and " + stationId2);
            return false;
        }

        try {
            // Add undirected edge using Bag implementation
            adjacencyList.get(stationId1).add(stationId2);
            adjacencyList.get(stationId2).add(stationId1);
            return true;
        } catch (RuntimeException e) {
            System.err.println("Error adding route: " + e.getMessage());
            return false;
        }
    }

    /**
     * Removes a direct route between two gas stations. Note: Since Bag ADT
     * doesn't support removal, this creates a new Bag without the specified
     * connection.
     *
     * @param stationId1 ID of the first gas station
     * @param stationId2 ID of the second gas station
     * @return true if route was removed, false otherwise
     */
    public boolean removeRoute(int stationId1, int stationId2) {
        if (!stations.containsKey(stationId1) || !stations.containsKey(stationId2)) {
            return false;
        }

        // Since Bag doesn't support removal, we reconstruct the bags
        adjacencyList.put(stationId1, rebuildBagWithout(adjacencyList.get(stationId1), stationId2));
        adjacencyList.put(stationId2, rebuildBagWithout(adjacencyList.get(stationId2), stationId1));

        return true;
    }

    /**
     * Helper method to rebuild a Bag without a specific element. This
     * workaround is needed because the Bag ADT doesn't support removal.
     *
     * @param originalBag The original bag
     * @param excludeId The ID to exclude from the new bag
     * @return New bag without the excluded element
     */
    private Bag<Integer> rebuildBagWithout(Bag<Integer> originalBag, int excludeId) {
        Bag<Integer> newBag = new FixedCapacityBag<>(50);
        for (int neighborId : originalBag) {
            if (neighborId != excludeId) {
                newBag.add(neighborId);
            }
        }
        return newBag;
    }

    /**
     * Gets all directly connected gas stations from a given station. This uses
     * the Bag implementation to return connected neighbors.
     *
     * @param stationId ID of the gas station
     * @return Bag containing IDs of directly connected stations
     */
    public Bag<Integer> getDirectlyConnectedStations(int stationId) {
        if (adjacencyList.containsKey(stationId)) {
            return adjacencyList.get(stationId);
        }
        return new FixedCapacityBag<>(1); // Empty bag
    }

    /**
     * Converts Bag of station IDs to List of GasStation objects for
     * convenience.
     *
     * @param stationId ID of the gas station
     * @return List of directly connected GasStation objects
     */
    public List<GasStation> getConnectedStationObjects(int stationId) {
        List<GasStation> connectedStations = new ArrayList<>();
        Bag<Integer> connectedIds = getDirectlyConnectedStations(stationId);

        for (int neighborId : connectedIds) {
            connectedStations.add(stations.get(neighborId));
        }
        return connectedStations;
    }

    /**
     * Checks if two gas stations are directly connected by a route.
     *
     * @param stationId1 ID of the first gas station
     * @param stationId2 ID of the second gas station
     * @return true if stations are directly connected, false otherwise
     */
    public boolean areDirectlyConnected(int stationId1, int stationId2) {
        if (!adjacencyList.containsKey(stationId1)) {
            return false;
        }

        Bag<Integer> neighbors = adjacencyList.get(stationId1);
        for (int neighborId : neighbors) {
            if (neighborId == stationId2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Performs Breadth-First Search starting from a specific gas station. This
     * is the core algorithm for autonomous vehicle route planning, finding the
     * shortest path (minimum hops) between stations.
     *
     * @param startStationId ID of the starting gas station
     * @param maxDistance Maximum number of hops to explore
     * @return Map of station IDs to their distance from start station
     */
    public Map<Integer, Integer> breadthFirstSearch(int startStationId, int maxDistance) {
        Map<Integer, Integer> distances = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        // Initialize BFS from starting station
        queue.offer(startStationId);
        visited.add(startStationId);
        distances.put(startStationId, 0);

        System.out.println("\n=== BFS ROUTE EXPLORATION ===");
        System.out.println("Starting from: " + stations.get(startStationId).getCompactInfo());
        System.out.println("Maximum exploration distance: " + maxDistance + " hops");
        System.out.println("\nBFS Traversal Progress:");

        while (!queue.isEmpty()) {
            int currentStationId = queue.poll();
            int currentDistance = distances.get(currentStationId);

            System.out.println("  Exploring: " + stations.get(currentStationId).getCompactInfo()
                    + " (Distance: " + currentDistance + " hops)");

            // Stop if we've reached maximum distance
            if (currentDistance >= maxDistance) {
                continue;
            }

            // Explore all directly connected stations using Bag
            Bag<Integer> neighbors = adjacencyList.get(currentStationId);
            for (int neighborId : neighbors) {
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    distances.put(neighborId, currentDistance + 1);
                    queue.offer(neighborId);

                    System.out.println("    Found route to: "
                            + stations.get(neighborId).getCompactInfo()
                            + " (Distance: " + (currentDistance + 1) + " hops)");
                }
            }
        }

        System.out.println("BFS Complete. Explored " + distances.size() + " stations.");
        return distances;
    }

    /**
     * Finds the shortest route between two gas stations using BFS. Critical for
     * autonomous vehicles to plan efficient refueling routes.
     *
     * @param startId Starting gas station ID
     * @param targetId Target gas station ID
     * @return List of station IDs representing the shortest path, or null if no
     * path exists
     */
    public List<Integer> findShortestRoute(int startId, int targetId) {
        if (!stations.containsKey(startId) || !stations.containsKey(targetId)) {
            return null;
        }

        if (startId == targetId) {
            List<Integer> samePath = new ArrayList<>();
            samePath.add(startId);
            return samePath;
        }

        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(startId);
        visited.add(startId);
        parent.put(startId, null);

        // BFS to find shortest path
        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == targetId) {
                // Reconstruct path from target back to start
                List<Integer> path = new ArrayList<>();
                int node = targetId;
                while (node != -1) {
                    path.add(0, node); // Add to beginning
                    Integer parentNode = parent.get(node);
                    node = (parentNode == null) ? -1 : parentNode;
                }
                return path;
            }

            // Explore neighbors using Bag
            Bag<Integer> neighbors = adjacencyList.get(current);
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        return null; // No path found
    }

    /**
     * Gets a gas station by its ID.
     *
     * @param stationId ID of the gas station
     * @return GasStation object or null if not found
     */
    public GasStation getGasStation(int stationId) {
        return stations.get(stationId);
    }

    /**
     * Gets all gas stations in the network.
     *
     * @return Collection of all GasStation objects
     */
    public Collection<GasStation> getAllGasStations() {
        return stations.values();
    }

    /**
     * Gets the total number of gas stations in the network.
     *
     * @return Number of gas stations
     */
    public int getStationCount() {
        return stations.size();
    }

    /**
     * Gets the total number of routes in the network.
     *
     * @return Number of routes (edges)
     */
    public int getRouteCount() {
        int count = 0;
        for (Bag<Integer> connections : adjacencyList.values()) {
            count += connections.size();
        }
        return count / 2; // Divide by 2 because each route is counted twice (undirected)
    }

    /**
     * Displays the complete gas station network structure. Shows all stations
     * and their direct connections for visualization.
     */
    public void displayNetwork() {
        System.out.println("\n=== GAS STATION NETWORK STRUCTURE ===");
        System.out.println("Total Stations: " + getStationCount());
        System.out.println("Total Routes: " + getRouteCount());
        System.out.println("\nStation Connections:");

        for (GasStation station : stations.values()) {
            System.out.println("\n" + station.getCompactInfo());
            Bag<Integer> connections = adjacencyList.get(station.getStationId());

            if (connections.isEmpty()) {
                System.out.println("  No direct routes (isolated station)");
            } else {
                System.out.print("  Connected to: ");
                boolean first = true;
                for (int connectedId : connections) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(stations.get(connectedId).getStationName()
                            + " (ID:" + connectedId + ")");
                    first = false;
                }
                System.out.println();
            }
        }
        System.out.println("=====================================\n");
    }
}
