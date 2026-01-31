package service;

import exception.InvalidVehicleException;
import exception.ParkingFullException;
import model.Slot;
import model.Ticket;
import model.Vehicle;
import model.VehicleType;
import strategy.ParkingFeeStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static ParkingLot instance;
    private List<Slot> slots;
    private Map<String, Ticket> activeTickets;
    private ParkingFeeStrategy feeStrategy;
    private int ticketCounter = 1;

    // Singleton for simplicity (optional, but good for "Manager" classes)
    private ParkingLot() {
        this.slots = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void initialize(Map<VehicleType, Integer> capacity, ParkingFeeStrategy strategy) {
        this.feeStrategy = strategy;
        this.slots.clear();
        this.activeTickets.clear();
        this.ticketCounter = 1;

        int slotId = 1;
        for (Map.Entry<VehicleType, Integer> entry : capacity.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                slots.add(new Slot(slotId++, entry.getKey()));
            }
        }
        System.out.println("Parking Lot initialized with " + slots.size() + " slots.");
    }

    public Ticket park(Vehicle vehicle) {
        if (activeTickets.containsKey(vehicle.getLicensePlate())) {
            throw new InvalidVehicleException(
                    "Vehicle with license plate " + vehicle.getLicensePlate() + " is already parked.");
        }

        Slot availableSlot = findAvailableSlot(vehicle.getType());
        if (availableSlot == null) {
            throw new ParkingFullException("No " + vehicle.getType() + " slots available.");
        }

        availableSlot.park(vehicle);
        Ticket ticket = new Ticket(ticketCounter++, availableSlot.getSlotNumber(), vehicle.getLicensePlate(),
                vehicle.getType());
        activeTickets.put(vehicle.getLicensePlate(), ticket);

        System.out.println("Vehicle parked. Ticket ID: " + ticket.getId());
        return ticket;
    }

    public double unpark(int ticketId) {
        // Find ticket by ID (In a real DB this is easy, here we filter map)
        Ticket ticketToRemove = null;
        for (Ticket ticket : activeTickets.values()) {
            if (ticket.getId() == ticketId) {
                ticketToRemove = ticket;
                break;
            }
        }

        if (ticketToRemove == null) {
            throw new InvalidVehicleException("Invalid Ticket ID: " + ticketId);
        }

        Slot slot = findSlotByNumber(ticketToRemove.getSlotNumber());
        slot.unpark();
        activeTickets.remove(ticketToRemove.getLicensePlate());

        long durationMillis = System.currentTimeMillis() - ticketToRemove.getEntryTime();
        // Convert millis to hours (ceil)
        long durationHours = (long) Math.ceil(durationMillis / (1000.0 * 60 * 60));

        // For simulation purpose, assume at least 1 hour if it's very fast
        if (durationHours == 0)
            durationHours = 1;

        double fee = feeStrategy.calculateFee(ticketToRemove.getVehicleType(), durationHours);
        System.out.println("Vehicle unparked. Fee: " + fee);
        return fee;
    }

    private Slot findAvailableSlot(VehicleType type) {
        for (Slot slot : slots) {
            if (slot.getType() == type && slot.isAvailable()) {
                return slot;
            }
        }
        return null;
    }

    private Slot findSlotByNumber(int slotNumber) {
        for (Slot slot : slots) {
            if (slot.getSlotNumber() == slotNumber) {
                return slot;
            }
        }
        return null; // Should ideally throw exception if internal state is consistent
    }
}
