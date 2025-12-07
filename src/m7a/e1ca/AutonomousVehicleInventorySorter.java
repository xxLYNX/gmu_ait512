package m7a.e1ca;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autonomous Vehicle Inventory Sorter using LSD (Least Significant Digit) Sort
 * Sorts vehicle inventory by VIN (Vehicle Identification Number) using LSD
 * algorithm VINs are standardized 17-character strings used for vehicle
 * registration and tracking
 *
 * @author Cullen Kelley
 */
public class AutonomousVehicleInventorySorter {

    /**
     * Main execution point Prints program header and executes VIN sorting for
     * autonomous vehicle inventory
     */
    public static void main(String[] args) {
        // Print program header
        System.out.println("========================================");
        System.out.println("M7A-E1-52 String Sort Creative Assignment");
        System.out.println("Autonomous Vehicle Inventory Sorter");
        System.out.println("Author: Cullen Kelley");
        System.out.println("Date: " + getCurrentDate());
        System.out.println("========================================\n");

        // Sample vehicle VINs (17-character standard format)
        String[] vins = {
            "1HGCV51387A004352",
            "2T1BFRHE8JC044186",
            "1FTFW1ET5DFC10305",
            "5FNRL6H76LB123456",
            "1GCHK23U15F109186",
            "3G1YY22G965371625",
            "2G1FB1E39D1234567",
            "4T1BF1AK5CU123456",
            "1D7RW2CT9AS123456",
            "5TDJXRFH0LS123456",
            "1FTSX2B89FF123456",
            "JH2RC5304LM123456"
        };

        System.out.println("UNSORTED VEHICLE INVENTORY (by VIN):");
        printInventory(vins);

        // Create sorter and execute LSD Sort
        VINLSDSorter sorter = new VINLSDSorter(vins);
        String[] sorted = sorter.sort();

        System.out.println("\nSORTED VEHICLE INVENTORY (by VIN):");
        printInventory(sorted);

        System.out.println("\n========================================");
        System.out.println("Sorting Complete - Inventory Ready");
        System.out.println("========================================");
    }

    /**
     * Helper method to print vehicle inventory
     *
     * @param vins array of VIN strings to display
     */
    private static void printInventory(String[] vins) {
        for (int i = 0; i < vins.length; i++) {
            System.out.println((i + 1) + ". VIN: " + vins[i]);
        }
    }

    /**
     * Get current date and time
     *
     * @return formatted date string with timestamp
     */
    private static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
