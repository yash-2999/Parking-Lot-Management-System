import exception.ParkingException;
import model.Bike;
import model.Car;
import model.Ticket;
import model.Truck;
import model.VehicleType;
import service.ParkingLot;
import strategy.DefaultFeeStrategy;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Parking Lot System Demo ===");

        // 1. Initialize Parking Lot
        ParkingLot parkingLot = ParkingLot.getInstance();
        Map<VehicleType, Integer> capacity = new HashMap<>();
        capacity.put(VehicleType.CAR, 2);
        capacity.put(VehicleType.BIKE, 2);
        capacity.put(VehicleType.TRUCK, 1);

        parkingLot.initialize(capacity, new DefaultFeeStrategy());

        // 2. Park Vehicles
        try {
            System.out.println("\n--- Parking Vehicles ---");
            Ticket t1 = parkingLot.park(new Car("KA-01-1234"));
            Ticket t2 = parkingLot.park(new Car("KA-02-5678"));
            Ticket t3 = parkingLot.park(new Bike("KA-03-9012"));
            
            // 3. Test Full Scenario
            System.out.println("\n--- Testing Full Scenario (Car) ---");
            parkingLot.park(new Car("KA-04-3456")); // Should fail
        } catch (ParkingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 4. Test Duplicate Parking
        try {
            System.out.println("\n--- Testing Duplicate Vehicle ---");
            parkingLot.park(new Car("KA-01-1234")); // Should fail
        } catch (ParkingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 5. Unpark Vehicle
        try {
            System.out.println("\n--- Unparking Vehicle ---");
            // Simulate waiting 2 hours (we manually calculate fee based on ticket logic in Unpark)
            // Since we can't sleep for 2 hours, the fee strategy calculates based on time diff.
            // Our Main runs fast, so it will be 0 hours -> min 1 hour charge.
            
            // We need a ticket ID to unpark. Let's assume we know the IDs or saved them.
            // For this demo, let's look at the output or just know it's 1, 2, 3...
            // Ticket ID 1 is the first Car.
            double fee = parkingLot.unpark(1);
            System.out.println("Paid: " + fee);
        } catch (ParkingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 6. Park in freed slot
        try {
            System.out.println("\n--- Parking in Freed Slot ---");
            parkingLot.park(new Car("KA-04-3456")); // Should now succeed
        } catch (ParkingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
