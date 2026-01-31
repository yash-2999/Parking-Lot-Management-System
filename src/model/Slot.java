package model;

public class Slot {
    private int slotNumber;
    private VehicleType type;
    private Vehicle parkedVehicle;

    public Slot(int slotNumber, VehicleType type) {
        this.slotNumber = slotNumber;
        this.type = type;
    }

    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    public void park(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public void unpark() {
        this.parkedVehicle = null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
