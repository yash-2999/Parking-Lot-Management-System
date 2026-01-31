package strategy;

import model.VehicleType;

public class DefaultFeeStrategy implements ParkingFeeStrategy {

    @Override
    public double calculateFee(VehicleType type, long durationHours) {
        double hourlyRate = 0;
        switch (type) {
            case BIKE:
                hourlyRate = 10;
                break;
            case CAR:
                hourlyRate = 20;
                break;
            case TRUCK:
                hourlyRate = 50;
                break;
        }
        // Charge for at least 1 hour
        long billableHours = Math.max(1, durationHours);
        return hourlyRate * billableHours;
    }
}
