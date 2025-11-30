package m6b.d3ca;

import ds.bag.Bag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * MSTPrimAlgorithm implements Prim's algorithm for finding minimum spanning
 * tree. Uses lazy implementation with priority queue.
 *
 * Author: Cullen Kelley Course: AIT-512 Data Structures and Algorithms
 * Assignment: M6B-D3-CA Creative Assignment
 */
public class MSTPrimAlgorithm {

    private ChargingNetwork network;
    private List<ChargingRoute> mstEdges;
    private double totalCost;
    private int startingHubId;

    /**
     * Constructs MST algorithm with a charging network.
     *
     * @param network The ChargingNetwork to analyze
     */
    public MSTPrimAlgorithm(ChargingNetwork network) {
        this.network = network;
        this.mstEdges = new ArrayList<>();
        this.totalCost = 0;
    }

    /**
     * Computes the minimum spanning tree using Prim's algorithm (lazy
     * implementation). Starts from specified hub.
     *
     * @param startingHubId ID of starting hub
     */
    public void computeMST(int startingHubId) {
        this.startingHubId = startingHubId;
        mstEdges.clear();
        totalCost = 0;

        Set<Integer> inMST = new HashSet<>();
        PriorityQueue<ChargingRoute> pq = new PriorityQueue<>();

        // Start with initial hub
        inMST.add(startingHubId);

        // Add all edges from starting hub to priority queue
        addEdgesToQueue(startingHubId, inMST, pq);

        // Process edges until MST is complete
        while (!pq.isEmpty() && inMST.size() < network.getHubCount()) {
            ChargingRoute edge = pq.poll();

            // Find which endpoint is not in MST (lazy - don't check before polling)
            int fromId = edge.getFromHub().getId();
            int toId = edge.getToHub().getId();

            boolean fromInMST = inMST.contains(fromId);
            boolean toInMST = inMST.contains(toId);

            // Skip if both endpoints already in MST (lazy implementation)
            if (fromInMST && toInMST) {
                continue;
            }

            // Accept edge and add new vertex to MST
            mstEdges.add(edge);
            totalCost += edge.getCost();

            int newHubId = fromInMST ? toId : fromId;
            inMST.add(newHubId);

            // Add edges from newly added hub
            addEdgesToQueue(newHubId, inMST, pq);
        }
    }

    /**
     * Helper method to add edges from a hub to the priority queue. Only adds
     * edges where the other endpoint is not in MST.
     *
     * @param hubId Hub to explore
     * @param inMST Set of hubs already in MST
     * @param pq Priority queue for edges
     */
    private void addEdgesToQueue(int hubId, Set<Integer> inMST, PriorityQueue<ChargingRoute> pq) {
        Bag<Integer> adjacentIds = network.getAdjacentHubs(hubId);

        for (int adjacentId : adjacentIds) {
            // Find route between these hubs
            ChargingRoute route = findRoute(hubId, adjacentId);
            if (route != null) {
                pq.add(route);
            }
        }
    }

    /**
     * Finds the route (edge) between two hubs.
     *
     * @param hubId1 First hub ID
     * @param hubId2 Second hub ID
     * @return ChargingRoute if found, null otherwise
     */
    private ChargingRoute findRoute(int hubId1, int hubId2) {
        for (ChargingRoute route : network.getAllRoutes()) {
            int fromId = route.getFromHub().getId();
            int toId = route.getToHub().getId();
            if ((fromId == hubId1 && toId == hubId2) || (fromId == hubId2 && toId == hubId1)) {
                return route;
            }
        }
        return null;
    }

    /**
     * Gets the MST edges found.
     *
     * @return List of edges in the MST
     */
    public List<ChargingRoute> getMSTEdges() {
        return new ArrayList<>(mstEdges);
    }

    /**
     * Gets the total cost of the MST.
     *
     * @return Sum of all edge costs in MST
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Gets the number of edges in the MST.
     *
     * @return Count of MST edges
     */
    public int getMSTEdgeCount() {
        return mstEdges.size();
    }

    /**
     * Displays the MST results.
     */
    public void displayMST() {
        System.out.println("\n=== MINIMUM SPANNING TREE (Prim's Algorithm) ===");
        System.out.println("Starting Hub: " + network.getHub(startingHubId).getName() + " (ID:" + startingHubId + ")");
        System.out.println("\nMST Edges (in selection order):");

        int edgeNumber = 1;
        for (ChargingRoute edge : mstEdges) {
            System.out.println("  " + edgeNumber + ". " + edge.getFromHub().getName()
                    + " <--> " + edge.getToHub().getName()
                    + " | Cost: $" + String.format("%.1f", edge.getCost()) + "K");
            edgeNumber++;
        }

        System.out.println("\nTotal Hubs in MST: " + (mstEdges.size() + 1));
        System.out.println("Total MST Edges: " + mstEdges.size());
        System.out.println("Total Infrastructure Cost: $" + String.format("%.1f", totalCost) + "K");
        System.out.println("==================================================\n");
    }
}
