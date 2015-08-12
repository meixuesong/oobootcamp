package oo.bootcamp.practice.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingBoyBase {
    protected List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoyBase(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
    }

    public Car pick(UUID ticket) {
        Car car = null;
        for (ParkingLot parkingLot : this.parkingLots) {
            car = parkingLot.pick(ticket);
            if (car != null) {
                break;
            }
        }
        return car;
    }

    protected int getNumberOfFreeSpace(ParkingLot parkingLot) {
        return parkingLot.getCapacity() - parkingLot.getParkedCars().size();
    }

    protected double getEmptyRate(ParkingLot parkingLot) {
        return parkingLot.getCapacity()==0 ? 0 : getNumberOfFreeSpace(parkingLot)/parkingLot.getCapacity();
    }
}
