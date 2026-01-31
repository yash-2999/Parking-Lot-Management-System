package strategy;

import model.VehicleType;

public interface ParkingFeeStrategy {
    double calculateFee(VehicleType type, long durationHours);
}
