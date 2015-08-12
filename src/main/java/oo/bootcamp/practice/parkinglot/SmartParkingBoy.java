package oo.bootcamp.practice.parkinglot;

import java.util.*;

public class SmartParkingBoy extends ParkingBoyBase {

    public SmartParkingBoy(List<ParkingLot> parkingLots){
        super(parkingLots);
    }

    public UUID park(Car car) {
        return getParkinglotWithMostFreeSpace().park(car);
    }

    public ParkingLot getParkinglotWithMostFreeSpace() {
        ParkingLot parkinglotWithMostFreeSpace = this.parkingLots.get(0);
        for (ParkingLot parkingLot : this.parkingLots) {
            if(getNumberOfFreeSpace(parkinglotWithMostFreeSpace)< getNumberOfFreeSpace(parkingLot)){
                parkinglotWithMostFreeSpace = parkingLot;
            }
        }
        return parkinglotWithMostFreeSpace;
    }
}
