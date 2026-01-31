package model;

public class Ticket {
    private int id;
    private int slotNumber;
    private String licensePlate;
    private long entryTime;
    private VehicleType vehicleType;

    public Ticket(int id, int slotNumber, String licensePlate, VehicleType vehicleType) {
        this.id = id;
        this.slotNumber = slotNumber;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.entryTime = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
