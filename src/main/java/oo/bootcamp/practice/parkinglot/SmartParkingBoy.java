package oo.bootcamp.practice.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SmartParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
    }

    public UUID park(Car car) {
        return getParkinglotWithMostFreeSpace().park(car);
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

    public ParkingLot getParkinglotWithMostFreeSpace() {
        ParkingLot parkinglotWithMostFreeSpace = this.parkingLots.get(0);
        for (ParkingLot parkingLot : this.parkingLots) {
            if(parkinglotWithMostFreeSpace.getNumberOfFreeSpace() < parkingLot.getNumberOfFreeSpace()){
                parkinglotWithMostFreeSpace = parkingLot;
            }
        }
        return parkinglotWithMostFreeSpace;
    }
}
