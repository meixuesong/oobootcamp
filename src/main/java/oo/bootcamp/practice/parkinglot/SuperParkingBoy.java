package oo.bootcamp.practice.parkinglot;

import java.util.List;
import java.util.UUID;

public class SuperParkingBoy extends ParkingBoyBase{
    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public UUID park(Car car) {
        return getParkinglotWithLargeFreeSpaceRate().park(car);
    }

    private ParkingLot getParkinglotWithLargeFreeSpaceRate() {
        ParkingLot parkinglotWithLargeFreeSpaceRate = this.parkingLots.get(0);
        for (ParkingLot parkingLot : this.parkingLots) {
            if(getEmptyRate(parkinglotWithLargeFreeSpaceRate)< getEmptyRate(parkingLot)){
                parkinglotWithLargeFreeSpaceRate = parkingLot;
            }
        }
        return parkinglotWithLargeFreeSpaceRate;
    }
}