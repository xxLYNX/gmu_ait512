package m5b.d1ca;

import ds.bag.Bag;
import ds.bag.FixedCapacityBag;

/**
 * Represents a gas station in the autonomous vehicle route planning network.
 * Each gas station serves as a vertex in the undirected graph, containing
 * location information, available fuel types, and station amenities. Uses the
 * standard Bag implementation for storing fuel types and amenities.
 */
public class GasStation {

    private int stationId;
    private String stationName;
    private String location;
    private double latitude;
    private double longitude;
    private Bag<String> fuelTypes;      // Uses standard Bag implementation
    private Bag<String> amenities;      // Uses standard Bag implementation
    private boolean isOperational;

    /**
     * Constructs a new gas station with specified properties.
     *
     * @param stationId Unique identifier for the gas station
     * @param stationName Brand name or identifier (e.g., "Shell", "BP")
     * @param location Human-readable location (e.g., "Downtown Main St")
     * @param latitude GPS latitude coordinate
     * @param longitude GPS longitude coordinate
     */
    public GasStation(int stationId, String stationName, String location,
            double latitude, double longitude) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fuelTypes = new FixedCapacityBag<>(10);    // Max 10 fuel types
        this.amenities = new FixedCapacityBag<>(20);    // Max 20 amenities
        this.isOperational = true;
    }

    /**
     * Adds a fuel type to this gas station using the Bag ADT.
     *
     * @param fuelType Type of fuel available (e.g., "Regular", "Premium",
     * "Diesel")
     */
    public void addFuelType(String fuelType) {
        try {
            fuelTypes.add(fuelType);
        } catch (RuntimeException e) {
            System.err.println("Cannot add fuel type - bag is full: " + fuelType);
        }
    }

    /**
     * Adds an amenity to this gas station using the Bag ADT.
     *
     * @param amenity Available amenity (e.g., "Car Wash", "Convenience Store")
     */
    public void addAmenity(String amenity) {
        try {
            amenities.add(amenity);
        } catch (RuntimeException e) {
            System.err.println("Cannot add amenity - bag is full: " + amenity);
        }
    }

    /**
     * Checks if this station offers a specific fuel type.
     *
     * @param fuelType Fuel type to check for
     * @return true if fuel type is available, false otherwise
     */
    public boolean hasFuelType(String fuelType) {
        for (String fuel : fuelTypes) {
            if (fuel.equals(fuelType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this station has a specific amenity.
     *
     * @param amenity Amenity to check for
     * @return true if amenity is available, false otherwise
     */
    public boolean hasAmenity(String amenity) {
        for (String available : amenities) {
            if (available.equals(amenity)) {
                return true;
            }
        }
        return false;
    }

    // Getter methods for station properties
    public int getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public String getLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public Bag<String> getFuelTypes() {
        return fuelTypes;
    }

    public Bag<String> getAmenities() {
        return amenities;
    }

    // Setter methods
    public void setOperational(boolean operational) {
        this.isOperational = operational;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Calculates approximate distance to another gas station using coordinates.
     * This is a simplified distance calculation for route planning purposes.
     *
     * @param other Another gas station
     * @return Approximate distance in kilometers
     */
    public double getDistanceTo(GasStation other) {
        double latDiff = this.latitude - other.latitude;
        double lonDiff = this.longitude - other.longitude;
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff) * 111.0; // Rough km conversion
    }

    /**
     * Returns a detailed string representation of the gas station.
     *
     * @return Formatted string with station information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Station[ID=%d, Name='%s', Location='%s']",
                stationId, stationName, location));
        sb.append("\n  Coordinates: (").append(latitude).append(", ").append(longitude).append(")");
        sb.append("\n  Operational: ").append(isOperational);

        // Display fuel types using Bag
        sb.append("\n  Fuel Types: ");
        if (fuelTypes.isEmpty()) {
            sb.append("None");
        } else {
            sb.append(fuelTypes.toString());
        }

        // Display amenities using Bag
        sb.append("\n  Amenities: ");
        if (amenities.isEmpty()) {
            sb.append("None");
        } else {
            sb.append(amenities.toString());
        }

        return sb.toString();
    }

    /**
     * Checks equality based on station ID.
     *
     * @param obj Object to compare with
     * @return true if stations have the same ID, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GasStation station = (GasStation) obj;
        return stationId == station.stationId;
    }

    /**
     * Returns hash code based on station ID.
     *
     * @return Hash code for this gas station
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(stationId);
    }

    /**
     * Returns a compact string representation for display in routes.
     *
     * @return Compact station description
     */
    public String getCompactInfo() {
        return String.format("%s (ID:%d) - %s", stationName, stationId, location);
    }
}
