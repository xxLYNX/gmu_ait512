package m6b.d3ca;

/**
 * ChargingHub represents a single AV charging station hub. Each hub is a vertex
 * in the charging network graph. Charging hub is implemented as an object.
 *
 * Author: Cullen Kelley Course: AIT-512 Data Structures and Algorithms
 * Assignment: M6B-D3-CA Creative Assignment
 */
public class ChargingHub {

    /**
     * Collection of instance variables used as properties that define a
     * charging hub.
     */
    private int id;
    private String name;
    private String region;
    private double latitude;
    private double longitude;

    /**
     * Constructs a ChargingHub with full details.
     *
     * @param id Unique identifier for the hub
     * @param name Name of the charging hub
     * @param region Geographic region where hub is located
     * @param latitude Latitude coordinate
     * @param longitude Longitude coordinate
     */
    public ChargingHub(int id, String name, String region, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the hub ID.
     *
     * @return The hub identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the hub name.
     *
     * @return The hub name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the hub region.
     *
     * @return The region where hub is located
     */
    public String getRegion() {
        return region;
    }

    /**
     * Gets the hub latitude.
     *
     * @return The latitude coordinate
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the hub longitude.
     *
     * @return The longitude coordinate
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns a string representation of the charging hub.
     *
     * @return Formatted string with hub information
     */
    @Override
    public String toString() {
        return String.format("%s (ID:%d) - %s at (%.2f, %.2f)",
                name, id, region, latitude, longitude);
    }

    /**
     * Checks equality based on hub ID.
     *
     * @param obj Object to compare
     * @return True if objects have same ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChargingHub other = (ChargingHub) obj;
        return id == other.id;
    }

    /**
     * Returns hash code based on ID.
     *
     * @return Hash code for the hub
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
