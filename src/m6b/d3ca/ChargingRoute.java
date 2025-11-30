package m6b.d3ca;

/**
 * ChargingRoute represents a direct route between two charging hubs. Each route
 * is an edge in the charging network graph with an associated cost.
 *
 * Author: Cullen Kelley Course: AIT-512 Data Structures and Algorithms
 * Assignment: M6B-D3-CA Creative Assignment
 */
public class ChargingRoute implements Comparable<ChargingRoute> {

    private ChargingHub fromHub;
    private ChargingHub toHub;
    private double cost;

    /**
     * Constructs a ChargingRoute between two hubs.
     *
     * @param fromHub Starting hub
     * @param toHub Destination hub
     * @param cost Infrastructure cost for this route (undirected)
     */
    public ChargingRoute(ChargingHub fromHub, ChargingHub toHub, double cost) {
        this.fromHub = fromHub;
        this.toHub = toHub;
        this.cost = cost;
    }

    /**
     * Gets the starting hub.
     *
     * @return The from hub
     */
    public ChargingHub getFromHub() {
        return fromHub;
    }

    /**
     * Gets the destination hub.
     *
     * @return The to hub
     */
    public ChargingHub getToHub() {
        return toHub;
    }

    /**
     * Gets the cost of this route.
     *
     * @return The infrastructure cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the other hub (for undirected edge). Given one hub, returns the
     * other endpoint of this edge.
     *
     * @param hub One endpoint of the edge
     * @return The other endpoint, or null if hub is not an endpoint
     */
    public ChargingHub getOtherHub(ChargingHub hub) {
        if (hub.getId() == fromHub.getId()) {
            return toHub;
        } else if (hub.getId() == toHub.getId()) {
            return fromHub;
        }
        return null;
    }

    /**
     * Compares routes by cost for priority queue sorting. Used for MST
     * algorithms that require edge sorting.
     *
     * @param other Route to compare with
     * @return Negative if this cost is less, positive if greater, 0 if equal
     */
    @Override
    public int compareTo(ChargingRoute other) {
        return Double.compare(this.cost, other.cost);
    }

    /**
     * Returns a string representation of the route.
     *
     * @return Formatted string with route information
     */
    @Override
    public String toString() {
        return String.format("%s -- %.1f -- %s",
                fromHub.getName(), cost, toHub.getName());
    }

    /**
     * Checks equality based on endpoints and cost.
     *
     * @param obj Object to compare
     * @return True if routes connect same hubs with same cost
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChargingRoute other = (ChargingRoute) obj;
        return (fromHub.equals(other.fromHub) && toHub.equals(other.toHub) && cost == other.cost)
                || (fromHub.equals(other.toHub) && toHub.equals(other.fromHub) && cost == other.cost);
    }

    /**
     * Returns hash code for the route.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(fromHub.getId() + toHub.getId());
    }
}
